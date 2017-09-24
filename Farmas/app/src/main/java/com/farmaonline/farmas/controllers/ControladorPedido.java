package com.farmaonline.farmas.controllers;

import com.farmaonline.farmas.model.CarroCompra;
import com.farmaonline.farmas.services.Request;
import com.farmaonline.farmas.services.Response;
import com.google.gson.Gson;

import java.io.IOException;

import util.Constants;

/**
 * Created by Romano on 20/09/2017.
 */

public class ControladorPedido {

    private static ControladorPedido instace;

    public static ControladorPedido getInstace() {

        if (instace == null) {
            instace = new ControladorPedido();
        }

        return instace;
    }

    public Response enviarPedido(CarroCompra carroCompra) {
        String json = new Gson().toJson(carroCompra);
        try {
            return Request.post(Constants.URI_POST_PEDIDO, Constants.APPLICATION_JSON, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
