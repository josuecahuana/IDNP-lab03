package com.example.lab3receptor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BatteryInfoReceiver extends BroadcastReceiver {

    public static final String EXTRA_BATTERY_INFO = "org.idnp.sampleintentbroadcatreceiver.BATTERY_INFO";
    private static final String TAG = "BatteryInfoReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String infoBateria = intent.getStringExtra(EXTRA_BATTERY_INFO);
        Log.d(TAG, "Información de batería recibida: " + infoBateria);
    }
}
