package com.farmaonline.farmas.view;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.controllers.ControladorChamada;
import com.farmaonline.farmas.controllers.ControladorUsuario;
import com.farmaonline.farmas.model.Usuario;
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

        String login = mLogin.getText().toString();
        String senha = mSenha.getText().toString();

        Usuario usuario = ControladorUsuario.get(this).consultarUsuario(login);

        boolean userIsValid = false;
        if (usuario == null) {
            Response response = ControladorUsuario.get(null).logar(mLogin.getText().toString(), mSenha.getText().toString());
            if (response.getHttpCode() == Constants.HTTP_SUCCESS) {
                salvarUsuarioLocalmente(login, senha);
                userIsValid = true;
            }
        } else if (usuario != null) {
            userIsValid = ControladorUsuario.get(null).validarUsuarioLocalmente(usuario, senha);
        }

        if (userIsValid) {
            startActivity(new Intent(this, ProductListActivity.class));
        } else {
            showMessage();
        }
    }

    private void showMessage () {
        Toast.makeText(this, getResources().getString(R.string.login_invalido), Toast.LENGTH_SHORT).show();
    }

    private void salvarUsuarioLocalmente (String login, String senha) {
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha(senha);
        ControladorUsuario.get(this).saveLocal(usuario);
    }
}
