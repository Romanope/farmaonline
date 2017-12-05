package com.farmaonline.farmas.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.controllers.ControladorEmpresa;
import com.farmaonline.farmas.model.Empresa;
import com.farmaonline.farmas.model.Product;
import com.farmaonline.farmas.viewutils.MapFragment;

import org.w3c.dom.Text;

public class DadosEmpresaActivity extends AppCompatActivity {

    private TextView mLblEmpresa;

    private TextView mLblCNPJ;

    private TextView mLblEndereco;

    private Button   mBtnOpenMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_empresa);

        MapFragment.mActivity = this;

        mLblEmpresa = (TextView) findViewById(R.id.lbl_empresa);
        mLblCNPJ = (TextView) findViewById(R.id.lbl_cnpj);
        mLblEndereco = (TextView) findViewById(R.id.lbl_endereco);
        mBtnOpenMaps = (Button) findViewById(R.id.btn_open_maps);

        Empresa empresa = ControladorEmpresa.get().obterDadosEmpresa();

        preencherCampos(empresa);

        addMapFragment(empresa);
    }

    private void preencherCampos (Empresa empresa) {

        mLblEmpresa.setText(empresa.getNome());
        mLblCNPJ.setText(empresa.getCnpj());
        mLblEndereco.setText(empresa.getEndereco());

    }

    protected void addMapFragment(Empresa empresa) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapFragment mapFragment = new MapFragment();
        Bundle args = new Bundle();
        args.putDouble("latitude", Double.valueOf(empresa.getLatitude()));
        args.putDouble("longitude", Double.valueOf(empresa.getLongitude()));
        args.putString("nomeMarcador", empresa.getNome());
        mapFragment.setArguments(args);
        fragmentTransaction.replace(R.id.container_map, mapFragment);
        fragmentTransaction.commit();
    }

    public void openMaps (View view) {

        Toast.makeText(this, "Opening maps... Waiting...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                MapFragment.mMap.setMyLocationEnabled(true);
            }
        }
    }
}
