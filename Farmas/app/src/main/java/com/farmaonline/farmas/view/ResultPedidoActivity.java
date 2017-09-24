package com.farmaonline.farmas.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.model.CarroCompra;
import com.google.gson.Gson;

public class ResultPedidoActivity extends ResultHelperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_pedido);

        mNrPedido = (TextView) findViewById(R.id.nr_pedido);
        mQtdItens = (TextView) findViewById(R.id.result_qtd_itens);
        mSubtotal = (TextView) findViewById(R.id.result_subtotal);
        mDesconto = (TextView) findViewById(R.id.result_desconto);
        mTotal    = (TextView) findViewById(R.id.result_total);
        mTempoEntrega = (TextView) findViewById(R.id.result_tempo_entrega);
        mNrTel = (TextView) findViewById(R.id.lbl_nr_fone);
        btnInicio = (Button) findViewById(R.id.btn_inicio);

        String jsonPedido = getIntent().getStringExtra("pedido");
        CarroCompra carroCompra = new Gson().fromJson(jsonPedido, CarroCompra.class);
        setDadosPedido(carroCompra);
    }
}
