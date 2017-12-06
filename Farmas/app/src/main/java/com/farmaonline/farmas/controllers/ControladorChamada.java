package com.farmaonline.farmas.controllers;

import com.farmaonline.farmas.services.Request;
import com.google.gson.Gson;

import java.io.IOException;

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
            Request.post(Constants.URI_ENVIAR_NUMEROS_LIGACOES_RECEBIDAS, Constants.APPLICATION_JSON, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Number {
        private String numero;
        public Number (String number) {
            this.numero = number;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }
    }
}
