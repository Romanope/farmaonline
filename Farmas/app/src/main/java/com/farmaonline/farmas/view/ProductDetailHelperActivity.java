package com.farmaonline.farmas.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.model.Product;
import com.farmaonline.farmas.viewutils.MapFragment;
import com.squareup.picasso.Picasso;

import util.Constants;

/**
 * Created by Romano on 21/09/2017.
 */

public class ProductDetailHelperActivity extends AppCompatActivity {

    protected ImageView mImgProduct;

    protected TextView mPrice;

    protected TextView mNameProduct;

    protected ImageView mLogoFarma;

    protected TextView nNomeFarma;

    protected TextView mFoneFarma;

    protected TextView mEndereco;

    protected TextView mGotoMaps;


    protected void setValores(Product product) {
        mNameProduct.setText(product.getDescricao());
        mPrice.setText(product.getValor().toString());
        nNomeFarma.setText(product.getFarmacia().getNome());
        mFoneFarma.setText(product.getFarmacia().getFone());
        mEndereco.setText(getResources().getString(R.string.lbl_rua) + " " + product.getFarmacia().getEnderco().getRua() + ", " + product.getFarmacia().getEnderco().getNumero() + " - " + product.getFarmacia().getEnderco().getBairro() + ", " + product.getFarmacia().getEnderco().getCidade());
    }

    protected void addMapFragment(Product product) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapFragment mapFragment = new MapFragment();
        Bundle args = new Bundle();
        args.putDouble("latitude", Double.valueOf(product.getFarmacia().getEnderco().getLatitude()));
        args.putDouble("longitude", Double.valueOf(product.getFarmacia().getEnderco().getLongitude()));
        args.putString("nomeMarcador", product.getFarmacia().getNome());
        mapFragment.setArguments(args);
        fragmentTransaction.replace(R.id.container_map, mapFragment);
        fragmentTransaction.commit();
    }

    protected void downloadImages(Product product) {
        Picasso.with(this).load(Constants.URI_IMAGES + product.getNomeImagem()).resize(200, 300).into(mImgProduct);
        Picasso.with(this).load(Constants.URI_IMAGES + product.getFarmacia().getNomeLogo()).resize(300, 300).into(mLogoFarma);
    }

    protected void addEventGoToMaps(final Product product) {
        mGotoMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=" + product.getFarmacia().getEnderco().getLatitude() + ", " + product.getFarmacia().getEnderco().getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
}
