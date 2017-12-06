package com.farmaonline.farmas.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.farmaonline.farmas.controllers.ControladorChamada;

/**
 * Created by Romano on 05/12/2017.
 * Receiver utilizado quando o aparelho recebe uma ligação
 * o número da chamada é salva em um banco de dados externo
 * através de uma chamada HTTP a uma API REST
 */

public class SaveNumberCallsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            ControladorChamada.get().sendNumberToRestService(number);
        }
    }
}
