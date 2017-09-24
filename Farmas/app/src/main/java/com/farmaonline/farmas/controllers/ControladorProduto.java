package com.farmaonline.farmas.controllers;

import com.farmaonline.farmas.model.Endereco;
import com.farmaonline.farmas.model.Farmacia;
import com.farmaonline.farmas.model.Product;
import com.farmaonline.farmas.services.Request;
import com.farmaonline.farmas.services.Response;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import util.Constants;

/**
 * Created by Romano on 21/09/2017.
 */

public class ControladorProduto {

    public static List<Product> parserToList(JSONArray jsonArray) {

        List<Product> products = new ArrayList<Product>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Farmacia farmacia = new Farmacia(jsonObject.getString("empresa"), jsonObject.getString("nomeLogoFarmacia"), jsonObject.getString("fone"));
                Endereco endereco = new Endereco(jsonObject.getString("rua"), jsonObject.getString("nrEndereco"), jsonObject.getString("bairro"), jsonObject.getString("cidade"), jsonObject.getString("latitude"), jsonObject.getString("longitude"));
                farmacia.setEnderco(endereco);
                products.add(new Product(jsonObject.getString("descricao"), farmacia, new BigDecimal(jsonObject.getString("preco")), jsonObject.getString("nomeImagem")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public static JSONArray getAllProducts() {
        try {

            Response response = Request.get(Constants.URI_ALL_PRODUCTS, Constants.APPLICATION_JSON, "");

            String json = response.getContent();

            return new JSONArray(json);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
