package com.farmaonline.farmas.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.UsuarioDAO;
import com.farmaonline.farmas.controllers.ControladorUsuario;
import com.farmaonline.farmas.database.SQLiteDatabaseHandler;
import com.farmaonline.farmas.model.Usuario;
import com.farmaonline.farmas.services.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import util.Constants;
import util.Util;

/**
 * Created by Romano on 24/09/2017.
 */

public class AddUserHelperActivity extends AppCompatActivity {

    protected static final int RESQUEST_PHOTO = 1;

    protected static final String DIR_IMG = "farma-photos";

    protected static final String NM_PHOTO = "user.png";

    protected EditText mTextNome;

    protected EditText mTextSenha;

    protected EditText mTextEmail;

    protected ImageButton mBtnCam;

    protected ImageView mImgPhotoUser;

    SQLiteDatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);

        mTextNome = (EditText) findViewById(R.id.add_user_input_nome);
        mTextSenha = (EditText) findViewById(R.id.add_user_input_senha);
        mTextEmail = (EditText) findViewById(R.id.add_user_input_email);
        mBtnCam = (ImageButton) findViewById(R.id.btn_cam);
        mImgPhotoUser = (ImageView) findViewById(R.id.img_photo_user);
        mImgPhotoUser.setImageBitmap(loadImage(getPathImage().getAbsolutePath()));

        handler = new SQLiteDatabaseHandler(getBaseContext());
    }

    protected File getPathImage() {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());

        File directory = cw.getDir(DIR_IMG, Context.MODE_PRIVATE);

        return directory;
    }

    protected void saveImage(Bitmap images) {

        File directory = getPathImage();

        File path = new File(directory, NM_PHOTO);

        FileOutputStream output = null;
        try {
            output = new FileOutputStream(path);

            images.compress(Bitmap.CompressFormat.PNG, 100, output);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected Bitmap loadImage(String path) {

        Bitmap image = null;
        try {
            File file = new File(path, NM_PHOTO);
            image = BitmapFactory.decodeStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return image;
    }

    public void save(View v) throws Exception {

        if (dadosValidos()) {
            String login = mTextNome.getText().toString();
            String json = createJson();
            Response response = ControladorUsuario.get(null).saveUser(json.toString());
            if (response.getHttpCode() == Constants.HTTP_CREATED) {
                saveLocal();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("login", login);
                startActivity(intent);
            }
        } else {
            Toast.makeText(getBaseContext(), getResources().getString(R.string.erro_validacao_cadastro), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveLocal () {

        Usuario usuario = new Usuario();

        usuario.setLogin(mTextNome.getText().toString());
        usuario.setSenha(mTextSenha.getText().toString());
        usuario.setEmail(mTextEmail.getText().toString());

        long id = UsuarioDAO.get(this).save(usuario);
        Toast.makeText(this, "ID INSERIDO: " + id, Toast.LENGTH_LONG).show();
    }

    protected String createJson() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("login", mTextNome.getText());
        json.put("senha", mTextSenha.getText());
        json.put("email", mTextEmail.getText());

        return json.toString();
    }

    protected boolean  dadosValidos() {
        if (Util.isNullOrEmpty(mTextNome.getText().toString())
                || Util.isNullOrEmpty(mTextSenha.getText().toString())
                || Util.isNullOrEmpty(mTextEmail.getText().toString())) {
            return false;
        }
        return true;
    }
}
