package com.farmaonline.farmas.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.controllers.ControladorChamada;
import com.farmaonline.farmas.model.Number;
import com.farmaonline.farmas.viewutils.ChamadaAdapter;

import java.io.IOException;
import java.util.List;

public class ChamadasActivity extends AppCompatActivity {

    private ListView mLvChamadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamadas);
        mLvChamadas = (ListView) findViewById(R.id.list_chamadas);
        try {
            List<Number> chamadas = ControladorChamada.get().listarChamadas();
            ChamadaAdapter chamadaAdapter = new ChamadaAdapter(this, 0, chamadas);
            mLvChamadas.setAdapter(chamadaAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
