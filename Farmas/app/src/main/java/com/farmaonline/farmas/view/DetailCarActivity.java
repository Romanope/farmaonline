package com.farmaonline.farmas.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.farmaonline.farmas.controllers.ControladorPedido;
import com.farmaonline.farmas.viewutils.FragmentCar;
import com.farmaonline.farmas.viewutils.ProductAdapter;
import com.farmaonline.farmas.R;
import com.farmaonline.farmas.services.Response;
import com.farmaonline.farmas.controllers.ControladorCarroCompra;
import com.farmaonline.farmas.model.CarroCompra;

import util.Constants;
import util.Util;

public class DetailCarActivity extends AppCompatActivity {

    private ListView mListView;

    private Button mBtnCancelar;

    private Button mBtnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_car);
        setTitle(getResources().getString(R.string.title_activity_order));
        mListView = (ListView) findViewById(R.id.list_detail_car);
        mBtnCancelar = (Button) findViewById(R.id.btn_cancelar);
        mBtnFinalizar = (Button) findViewById(R.id.btn_finalizar);
        loadList();
        addTotals();
        addEventFinish();
        addEventCancel();
    }

    private void loadList() {
        ProductAdapter productAdapter = new ProductAdapter(this, 0, ControladorCarroCompra.getUniqueInstanceCarroCompra().getProducts(), false);
        mListView.setAdapter(productAdapter);
    }

    private void addTotals() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CarroCompra carroCompra = ControladorCarroCompra.getUniqueInstanceCarroCompra();
        transaction.add(R.id.fragment_header, FragmentCar.newInstance(String.valueOf(carroCompra.getProducts().size()), Util.decimalFormat(carroCompra.getTotal()), Util.decimalFormat(carroCompra.getDesconto()), Util.decimalFormat(carroCompra.getSubtotal()), false));
        transaction.commit();
    }

    private void addEventCancel() {
        mBtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void addEventFinish() {
        mBtnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Response response = ControladorPedido.getInstace().enviarPedido(ControladorCarroCompra.getUniqueInstanceCarroCompra());
            if (response.getHttpCode() == Constants.HTTP_SUCCESS) {
                ControladorCarroCompra.limparCarroCompra();
                Intent intent = new Intent(getBaseContext(), ResultPedidoActivity.class);
                intent.putExtra("pedido", response.getContent());
                startActivity(intent);
                return;
            }
            Toast.makeText(getBaseContext(), getResources().getString(R.string.msg_erro_finalizar_pedido), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.title_dialog)).setPositiveButton(R.string.lbl_sim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ControladorCarroCompra.limparCarroCompra();
                startActivity(new Intent(getBaseContext(), ProductListActivity.class));
            }
        });
        builder.setNegativeButton(R.string.lbl_nao, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.create().show();
    }
}
