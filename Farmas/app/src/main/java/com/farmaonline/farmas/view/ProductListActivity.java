package com.farmaonline.farmas.view;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.farmaonline.farmas.R;
import com.farmaonline.farmas.controllers.ControladorCarroCompra;
import com.farmaonline.farmas.controllers.ControladorProduto;
import com.farmaonline.farmas.model.CarroCompra;
import com.farmaonline.farmas.model.Endereco;
import com.farmaonline.farmas.model.Farmacia;
import com.farmaonline.farmas.model.Product;
import com.farmaonline.farmas.viewutils.FragmentCar;
import com.farmaonline.farmas.viewutils.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.util.EntityUtils;
import util.Constants;
import util.Util;

public class ProductListActivity extends ProductListHelperActivity {

    private static final int NOTIFICATION_ID = 001;

    private static final long TEN_SECONDS = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_listview);

        mListView = (ListView) findViewById(R.id.listview);

        try {
            findAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        addCart();

       try {
           showNotification();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dados_empresa, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.dados_empresa) {
            Intent intent = new Intent(this, DadosEmpresaActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.chamadas) {
            Intent intent = new Intent(this, ChamadasActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    private void showNotification () throws InterruptedException {
        final Context context = this;
        new Thread(new Runnable() {
            @Override
            public void run()  {
                try {
                    Thread.sleep(TEN_SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context).
                        setSmallIcon(R.drawable.common_full_open_on_phone).
                        setContentTitle(context.getString(R.string.app_name)).
                        setContentText(context.getString(R.string.message_notification));
                NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotifyMgr.notify(NOTIFICATION_ID, mBuilder.build());
            }
        }).start();
    }
}
