package com.example.lab3receptor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

public class MoonBroadcastReceiver extends BroadcastReceiver {

    public static final String EXTRA_MOON_PHASE = "org.idnp.sampleintentbroadcatreceiver.MoonBroadcastReceiver.EXTRA_MOON_PHASE";
    private static final String TAG = "MoonBroadcastReceiver";
    private TextView textView;

    public MoonBroadcastReceiver() {
    }

    public MoonBroadcastReceiver(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(EXTRA_MOON_PHASE);
        if (message != null && textView != null) {
            // Asegúrate de actualizar el TextView en el hilo principal
            textView.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(message);
                }
            });
        }
        Log.d(TAG, "Mensaje recibido: " + message);
    }
}
