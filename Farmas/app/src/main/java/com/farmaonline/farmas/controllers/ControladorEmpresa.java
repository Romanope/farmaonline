package com.farmaonline.farmas.controllers;

import com.farmaonline.farmas.model.Empresa;
import com.farmaonline.farmas.services.Request;
import com.farmaonline.farmas.services.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import util.Constants;

/**
 * Created by Romano on 04/12/2017.
 */

public class ControladorEmpresa {

    private static ControladorEmpresa instance;

    public static ControladorEmpresa get () {

        if (instance == null) {
            instance = new ControladorEmpresa();
        }

        return instance;
    }

    public Empresa obterDadosEmpresa () {

        try {
            Response response = Request.get(Constants.URI_CONSULTAR_DADOS_FARMA_ONLINE, Constants.APPLICATION_JSON, "");
            String json = response.getContent();

            JSONObject object = new JSONObject(json);

            String nome = object.getString("nome");
            String cnpj = object.getString("cnpj");
            String rua = object.getString("rua");
            String numero = object.getString("numero");
            String bairro = object.getString("bairro");
            String cidade = object.getString("cidade");
            String estado = object.getString("estado");
            String latitude = object.getString("latitude");
            String longitude = object.getString("longitude");
            String endereco = object.getString("endereco");

            Empresa empresa = new Empresa();
            empresa.setNome(nome);
            empresa.setCnpj(cnpj);
            empresa.setRua(rua);
            empresa.setNumero(numero);
            empresa.setBairro(bairro);
            empresa.setCidade(cidade);
            empresa.setEstado(estado);
            empresa.setLatitude(latitude);
            empresa.setLongitude(longitude);
            empresa.setEndereco(endereco);

            return empresa;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
