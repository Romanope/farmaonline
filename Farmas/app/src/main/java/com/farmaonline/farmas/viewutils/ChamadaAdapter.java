package com.farmaonline.farmas.viewutils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.controllers.ControladorCarroCompra;
import com.farmaonline.farmas.model.Number;
import com.farmaonline.farmas.model.Product;
import com.farmaonline.farmas.view.ProductDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import util.Constants;
import util.Util;

/**
 * Created by Romano on 11/09/2017.
 */

public class ChamadaAdapter extends ArrayAdapter<Number> {

    private final Context mContext;

    private final List<Number> chamadas;

    public ChamadaAdapter(Context context, int resources, List<Number> chamadas) {
        super(context, resources, chamadas);
        this.mContext = context;
        this.chamadas = chamadas;
    }

    @Override
    public int getCount() {
        return chamadas.size();
    }

    @Nullable
    @Override
    public Number getItem(int position) {
        return chamadas.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Number chamada = getItem(position);

        View view;
        ViewHolderChamada viewHolderChamada;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.dados_chamada, parent, false);
            viewHolderChamada = new ViewHolderChamada(view);
            view.setTag(viewHolderChamada);
        } else {
            view = convertView;
            viewHolderChamada = (ViewHolderChamada) view.getTag();
        }

        viewHolderChamada.mCallNumber.setText(chamada.getNumero());

        return view;
    }
}
