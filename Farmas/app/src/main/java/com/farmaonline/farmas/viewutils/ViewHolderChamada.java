package com.farmaonline.farmas.viewutils;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.farmaonline.farmas.R;

/**
 * Created by Romano on 22/09/2017.
 */

public class ViewHolderChamada {

    public final TextView mCallNumber;

    public ViewHolderChamada(View view) {
        mCallNumber = (TextView) view.findViewById(R.id.call_number);
    }
}
