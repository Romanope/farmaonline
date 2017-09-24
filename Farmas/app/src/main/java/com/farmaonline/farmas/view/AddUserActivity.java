package com.farmaonline.farmas.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.controllers.ControladorUsuario;
import com.farmaonline.farmas.services.Response;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import util.Constants;
import util.Util;

public class AddUserActivity extends AppCompatActivity {

    private EditText mTextNome;

    private EditText mTextSenha;

    private EditText mTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);

        mTextNome = (EditText) findViewById(R.id.add_user_input_nome);
        mTextSenha = (EditText) findViewById(R.id.add_user_input_senha);
        mTextEmail = (EditText) findViewById(R.id.add_user_input_email);
    }
    public void save(View v) throws Exception {

        if (dadosValidos()) {
            String login = mTextNome.getText().toString();
            String json = createJson();
            Response response = ControladorUsuario.getInstance().saveUser(json.toString());
            if (response.getHttpCode() == Constants.HTTP_CREATED) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("login", login);
                startActivity(intent);
            }
        } else {
            Toast.makeText(getBaseContext(), getResources().getString(R.string.erro_validacao_cadastro), Toast.LENGTH_SHORT).show();
        }
    }

    private String createJson() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("login", mTextNome.getText());
        json.put("senha", mTextSenha.getText());
        json.put("email", mTextEmail.getText());

        return json.toString();
    }

    private boolean  dadosValidos() {
        if (Util.isNullOrEmpty(mTextNome.getText().toString())
                || Util.isNullOrEmpty(mTextSenha.getText().toString())
                || Util.isNullOrEmpty(mTextEmail.getText().toString())) {
            return false;
        }
        return true;
    }
}
