package com.farmaonline.farmas.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.model.Product;
import com.farmaonline.farmas.viewutils.MapFragment;
import com.squareup.picasso.Picasso;

import util.Constants;

public class ProductDetailActivity extends ProductDetailHelperActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        this.setTitle(getResources().getString(R.string.title_detalhes));

        mImgProduct = (ImageView) findViewById(R.id.img_product);
        mNameProduct = (TextView) findViewById(R.id.lbl_nm_product);
        mPrice = (TextView) findViewById(R.id.lbl_vl_product);
        mLogoFarma = (ImageView) findViewById(R.id.img_logo_farma);
        nNomeFarma = (TextView) findViewById(R.id.lbl_nome_farma);
        mFoneFarma = (TextView) findViewById(R.id.lbl_fone_farma);
        mEndereco = (TextView) findViewById(R.id.lbl_rua_farma);
        mGotoMaps = (TextView) findViewById(R.id.lbl_to_maps);

        final Product product = (Product) getIntent().getSerializableExtra("Product");

        setValores(product);
        downloadImages(product);
        addMapFragment(product);
        addEventGoToMaps(product);
    }
}
