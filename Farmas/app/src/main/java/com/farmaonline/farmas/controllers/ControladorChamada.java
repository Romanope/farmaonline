package com.farmaonline.farmas.controllers;

import com.farmaonline.farmas.model.Number;
import com.farmaonline.farmas.services.Request;
import com.farmaonline.farmas.services.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import util.Constants;

/**
 * Created by Romano on 05/12/2017.
 */

public class ControladorChamada {

    private static ControladorChamada instance;

    public static ControladorChamada get () {
        if (instance == null) {
            instance = new ControladorChamada();
        }
        return instance;
    }

    public void sendNumberToRestService (String number) {
        String json = new Gson().toJson(new Number(number));
        try {
            Request.post(Constants.URI_BASE_API_CHAMADAS, Constants.APPLICATION_JSON, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Number> listarChamadas () throws IOException {

        Response response = Request.get (Constants.URI_BASE_API_CHAMADAS, Constants.APPLICATION_JSON, "");

        String json = response.getContent();

        Type listType = new TypeToken<ArrayList<Number>>(){}.getType();

        List<Number> chamadas = new Gson().fromJson(json, listType);

        return chamadas;
    }
}
