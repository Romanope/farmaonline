package com.farmaonline.farmas.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.model.CarroCompra;

import java.util.Random;

import util.Util;

/**
 * Created by Romano on 20/09/2017.
 */

public class ResultHelperActivity extends AppCompatActivity {

    protected TextView mNrPedido;

    protected TextView mQtdItens;

    protected TextView mSubtotal;

    protected TextView mDesconto;

    protected TextView mTotal;

    protected TextView mTempoEntrega;

    protected TextView mNrTel;

    private String tel;

    protected Button btnInicio;

    protected void setDadosPedido(CarroCompra carroCompra) {
        String cifrao = getResources().getString(R.string.cifrao);
        mNrPedido.setText(getResources().getString(R.string.result_nr_pedido).concat(" ").concat(String.valueOf(carroCompra.getId())));
        mQtdItens.setText(getResources().getString(R.string.result_nr_itens).concat(" ").concat(String.valueOf(carroCompra.getProducts().size())));
        mSubtotal.setText(getResources().getString(R.string.frag_car_subtotal).concat(" ").concat(cifrao).concat(" ").concat(Util.decimalFormat(carroCompra.getSubtotal())));
        mDesconto.setText(getResources().getString(R.string.frag_car_desconto).concat(" ").concat(cifrao).concat(" ").concat(Util.decimalFormat(carroCompra.getDesconto())));
        mTotal.setText(getResources().getString(R.string.frag_car_total).concat(" ").concat(cifrao).concat(" ").concat(Util.decimalFormat(carroCompra.getTotal())));
        mTempoEntrega.setText(getResources().getString(R.string.msg_tempo_entrega).concat(" ").concat(random()).concat(" ").concat(getResources().getString(R.string.lbl_minuto)));

        tel = carroCompra.getProducts().iterator().next().getFarmacia().getFone().replace("-", "");
        mNrTel.setText(getResources().getString(R.string.msg_tel).concat(" ").concat(tel));

        addEventHome();
        addEventCall();
    }

    private String random() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100));
    }

    private void addEventCall() {
        mNrTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tel, null));
                startActivity(intent);
            }
        });
    }

    private void addEventHome() {
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), ProductListActivity.class));
            }
        });
    }
}
