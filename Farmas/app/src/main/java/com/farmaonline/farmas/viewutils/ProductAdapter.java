package com.farmaonline.farmas.viewutils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.farmaonline.farmas.view.ProductDetailActivity;
import com.farmaonline.farmas.R;
import com.farmaonline.farmas.controllers.ControladorCarroCompra;
import com.farmaonline.farmas.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import util.Constants;
import util.Util;

/**
 * Created by Romano on 11/09/2017.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    private final Context mContext;

    private boolean mExibirBotoes;

    private final List<Product> produtos;

    public  ProductAdapter(Context context, int resources, List<Product> productList, boolean exibirBotoes) {
        super(context, resources, productList);
        this.mContext = context;
        mExibirBotoes = exibirBotoes;
        this.produtos = productList;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Nullable
    @Override
    public Product getItem(int position) {
        return produtos.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Product p = getItem(position);

        View view;
        ViewHolderProduct viewHolderProduct;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.product_list, parent, false);
            viewHolderProduct = new ViewHolderProduct(view);
            view.setTag(viewHolderProduct);
        } else {
            view = convertView;
            viewHolderProduct = (ViewHolderProduct) view.getTag();
        }

        viewHolderProduct.productName.setText(p.getDescricao());

        viewHolderProduct.nomeFarma.setText(p.getFarmacia().getNome());

        viewHolderProduct.price.setText(mContext.getResources().getString(R.string.cifrao) + " " + Util.decimalFormat(p.getValor()));

        final Product product = getItem(position);

        viewHolderProduct.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControladorCarroCompra.addProductToListAnd(product);
                updateTotals();
            }
        });
        viewHolderProduct.btnDetailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (!mExibirBotoes) {
                ControladorCarroCompra.removeProduct(product);
                updateTotals();
                notifyDataSetChanged();
                return;
            }
            Intent intent = new Intent(mContext, ProductDetailActivity.class);
            intent.putExtra("Product", product);
            mContext.startActivity(intent);
            }
        });

        if (!mExibirBotoes) {
            viewHolderProduct.btn.setVisibility(View.INVISIBLE);
            viewHolderProduct.btnDetailProduct.setImageResource(R.drawable.subtract);
        }

        Picasso.with(getContext()).load(Constants.URI_IMAGES + p.getNomeImagem()).resize(100, 100).into(viewHolderProduct.image);
        return view;
    }

    private void updateTotals() {
        FragmentCar.updateTotal(Util.decimalFormat(ControladorCarroCompra.getUniqueInstanceCarroCompra().getTotal()));
        FragmentCar.updateSubtotal(Util.decimalFormat(ControladorCarroCompra.getUniqueInstanceCarroCompra().getSubtotal()));
        FragmentCar.updateQtdItems(String.valueOf(ControladorCarroCompra.getUniqueInstanceCarroCompra().getProducts().size()));
        Toast.makeText(mContext, mContext.getResources().getString(R.string.msg_add_product), Toast.LENGTH_SHORT).show();
    }
}
