package com.farmaonline.farmas.viewutils;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.farmaonline.farmas.R;

/**
 * Created by Romano on 22/09/2017.
 */

public class ViewHolderProduct {

    public final TextView productName;
    public final TextView nomeFarma;
    public final TextView price;
    public final ImageButton btn;
    public final ImageButton btnDetailProduct;
    public final ImageView image;

    public ViewHolderProduct(View view) {
        productName = (TextView) view.findViewById(R.id.lbl_list);
        nomeFarma = (TextView) view.findViewById(R.id.lbl_nome_farma);
        price = (TextView) view.findViewById(R.id.lbl_price);
        btn = (ImageButton) view.findViewById(R.id.btn_add_to_car);
        btnDetailProduct = (ImageButton) view.findViewById(R.id.btn_detail_product);
        image = (ImageView) view.findViewById(R.id.imageView);
    }
}
