package com.farmaonline.farmas.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.controllers.ControladorCarroCompra;
import com.farmaonline.farmas.controllers.ControladorProduto;
import com.farmaonline.farmas.model.CarroCompra;
import com.farmaonline.farmas.model.Product;
import com.farmaonline.farmas.viewutils.FragmentCar;
import com.farmaonline.farmas.viewutils.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import util.Util;

/**
 * Created by Romano on 21/09/2017.
 */

public class ProductListHelperActivity extends AppCompatActivity {

    protected ListView mListView;

    protected void findAll() throws IOException, JSONException {

        JSONArray jsonArray = ControladorProduto.getAllProducts();

        final List<Product> products = ControladorProduto.parserToList(jsonArray);

        ProductAdapter productAdapter = new ProductAdapter(this, 0, products, true);

        mListView.setAdapter(productAdapter);
    }

    protected void addCart() {
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        CarroCompra carroCompra = ControladorCarroCompra.getUniqueInstanceCarroCompra();
        transaction.add(R.id.fragment_footer, FragmentCar.newInstance(String.valueOf(carroCompra.getProducts().size()), Util.decimalFormat(carroCompra.getTotal()), Util.decimalFormat(carroCompra.getDesconto()), Util.decimalFormat(carroCompra.getSubtotal()), true));
        transaction.commit();
    }
}
