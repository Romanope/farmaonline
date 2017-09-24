package com.farmaonline.farmas.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.controllers.ControladorCarroCompra;
import com.farmaonline.farmas.controllers.ControladorProduto;
import com.farmaonline.farmas.model.CarroCompra;
import com.farmaonline.farmas.model.Endereco;
import com.farmaonline.farmas.model.Farmacia;
import com.farmaonline.farmas.model.Product;
import com.farmaonline.farmas.viewutils.FragmentCar;
import com.farmaonline.farmas.viewutils.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.util.EntityUtils;
import util.Constants;
import util.Util;

public class ProductListActivity extends ProductListHelperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_listview);

        mListView = (ListView) findViewById(R.id.listview);

        try {
            findAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        addCart();
    }
}
