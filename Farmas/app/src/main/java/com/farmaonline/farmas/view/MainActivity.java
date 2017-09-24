package com.farmaonline.farmas.view;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.controllers.ControladorUsuario;
import com.farmaonline.farmas.services.Response;

import util.Constants;

public class MainActivity extends AppCompatActivity {

    private EditText mLogin;

    private EditText mSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
        .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mLogin = (EditText) findViewById(R.id.txt_login);
        mSenha = (EditText) findViewById(R.id.txt_login_senha);
        setLogin();
    }

    private void setLogin() {
        Intent intent = getIntent();
        if (intent.getStringExtra("login") != null) {
            mLogin.setText(intent.getStringExtra("login"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_user) {
            Intent intent = new Intent(this, AddUserActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    public void login(View v) throws Exception {

        Response response = ControladorUsuario.getInstance().logar(mLogin.getText().toString(), mSenha.getText().toString());
        if (response.getHttpCode() == Constants.HTTP_SUCCESS) {
            startActivity(new Intent(this, ProductListActivity.class));
        } else if (response.getHttpCode() == Constants.HTTP_UNAUTHORIZED) {
            Toast.makeText(this, getResources().getString(R.string.login_invalido), Toast.LENGTH_SHORT).show();
        }
    }
}
