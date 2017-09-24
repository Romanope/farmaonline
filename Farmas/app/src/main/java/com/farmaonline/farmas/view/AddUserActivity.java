package com.farmaonline.farmas.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.controllers.ControladorUsuario;
import com.farmaonline.farmas.services.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import util.Constants;
import util.Util;

public class AddUserActivity extends AddUserHelperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);

        mTextNome = (EditText) findViewById(R.id.add_user_input_nome);
        mTextSenha = (EditText) findViewById(R.id.add_user_input_senha);
        mTextEmail = (EditText) findViewById(R.id.add_user_input_email);
        mBtnCam = (ImageButton) findViewById(R.id.btn_cam);
        mImgPhotoUser = (ImageView) findViewById(R.id.img_photo_user);
        addEventOpenCamera(mBtnCam);
        mImgPhotoUser.setImageBitmap(loadImage(getPathImage().getAbsolutePath()));
    }


    private void addEventOpenCamera(ImageButton btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, RESQUEST_PHOTO);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESQUEST_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImgPhotoUser.setImageBitmap(imageBitmap);
            saveImage(imageBitmap);
        }
    }
}
