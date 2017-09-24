package com.farmaonline.farmas.controllers;

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

    private ControladorUsuario() {

    }

    public static ControladorUsuario getInstance() {
        if (instance == null) {
            instance = new ControladorUsuario();
        }
        return instance;
    }

    public Response saveUser(String userJson) {

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
}
