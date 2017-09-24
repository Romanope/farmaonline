package com.farmaonline.farmas.viewutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.TextView;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.controllers.ControladorCarroCompra;
import com.farmaonline.farmas.view.DetailCarActivity;

import util.Constants;


public class FragmentCar extends Fragment {

    private String desconto;

    private String subtotal;

    private String total;

    private String qtdItens;

    private static FragmentCar currentInstance;

    private static String cifrao;

    private ImageButton btnDateilCar;

    private boolean showButton;

    private Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            desconto = getArguments().getString(Constants.DESCONTO);
            subtotal = getArguments().getString(Constants.SUB_TOTAL);
            total = getArguments().getString(Constants.TOTAL);
            qtdItens = getArguments().getString(Constants.QTD_ITENS);
            showButton = getArguments().getBoolean(Constants.SHOW_BUTTON);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_car, container, false);
        TextView lblItens = (TextView) view.findViewById(R.id.lbl_itens);
        TextView lblDesconto = (TextView) view.findViewById(R.id.lbl_desconto);
        TextView lbltotal = (TextView) view.findViewById(R.id.lbl_total);
        TextView lblSubtotal = (TextView) view.findViewById(R.id.lbl_subtotal);
        btnDateilCar = (ImageButton) view.findViewById(R.id.btn_datail_car);
        addOnClick();
        if (!showButton) {
            btnDateilCar.setVisibility(View.GONE);
        }

        if (desconto != null && total != null && subtotal != null) {
            cifrao = getResources().getString(R.string.cifrao);
            lblItens.setText(getResources().getString(R.string.frag_car_items) + " " + qtdItens);
            lblDesconto.setText(getResources().getString(R.string.frag_car_desconto) + cifrao + " " + desconto);
            lbltotal.setText(getResources().getString(R.string.frag_car_total) + cifrao + " " + total);
            lblSubtotal.setText(getResources().getString(R.string.frag_car_subtotal) + cifrao + " " + subtotal);
        }
        return view;
    }

    private void addOnClick() {
        btnDateilCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ControladorCarroCompra.getUniqueInstanceCarroCompra().getProducts().size() > 0) {
                    startActivity(new Intent(getContext(), DetailCarActivity.class));
                }
            }
        });
    }

    public static FragmentCar newInstance(String qtdItens, String total, String desconto, String subtotal, boolean showButton) {

        FragmentCar fragmentCar = new FragmentCar();

        Bundle args = new Bundle();
        args.putString(Constants.TOTAL, total);
        args.putString(Constants.SUB_TOTAL, subtotal);
        args.putString(Constants.DESCONTO, desconto);
        args.putString(Constants.QTD_ITENS, qtdItens);
        args.putBoolean(Constants.SHOW_BUTTON, showButton);

        fragmentCar.setArguments(args);

        currentInstance = fragmentCar;
        return  fragmentCar;
    }

    public static void updateTotal(String value) {
        if (currentInstance != null) {
            TextView textView = (TextView) currentInstance.getView().findViewById(R.id.lbl_total);
            textView.setText(currentInstance.getContext().getResources().getString(R.string.frag_car_total) + cifrao + " " + value);
        }
    }

    public static void updateQtdItems( String value) {
        if (currentInstance != null) {
            TextView textView = (TextView) currentInstance.getView().findViewById(R.id.lbl_itens);
            textView.setText(currentInstance.getContext().getResources().getString(R.string.frag_car_subtotal) +  cifrao + " " + value);
        }
    }

    public static void updateSubtotal(String value) {
        if (currentInstance != null) {
            TextView textView = (TextView) currentInstance.getView().findViewById(R.id.lbl_subtotal);
            textView.setText(currentInstance.getContext().getResources().getString(R.string.frag_car_items) + cifrao + " " + value);
        }
    }
}
