package com.farmaonline.farmas.controllers;

import android.content.Context;

import com.farmaonline.farmas.UsuarioDAO;
import com.farmaonline.farmas.model.Usuario;
import com.farmaonline.farmas.services.Request;
import com.farmaonline.farmas.services.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import util.Constants;

/**
 * Created by Romano on 20/09/2017.
 */

public class ControladorUsuario {

    private static ControladorUsuario instance;

    private Context mContex;

    private ControladorUsuario() {

    }

    public static ControladorUsuario get(Context context) {
        if (instance == null) {
            instance = new ControladorUsuario();
        }
        instance.setContext(context);
        return instance;
    }

    private Context getContex () {
        return this.mContex;
    }

    private void setContext (Context context) {
        this.mContex = context;
    }

    public Response saveUser (String userJson) {

        if (userJson == null) {
            throw new IllegalArgumentException();
        }

        try {
            return  Request.post(Constants.URI_POST_USER, Constants.APPLICATION_JSON, userJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Response logar(String login, String senha) throws JSONException {

        JSONObject json = new JSONObject();

        json.put("login", login);
        json.put("senha", senha);

        try {
            return Request.post(Constants.URI_LOGIN, Constants.APPLICATION_JSON, json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean validarUsuarioLocalmente (Usuario usuario,String senhaDigitada) {

        return usuario.getSenha().equals(senhaDigitada);
    }

    public Usuario consultarUsuario (String login) {

        return UsuarioDAO.get(instance.getContex()).getUserByLogin(login);
    }

    public long saveLocal (Usuario usuario) {

        return  UsuarioDAO.get(instance.getContex()).save(usuario);
    }
}
