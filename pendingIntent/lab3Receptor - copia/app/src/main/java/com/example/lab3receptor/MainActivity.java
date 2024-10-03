package com.example.lab3receptor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "receptorBroadcast";
    private TextView estadoBateriaTexto;

    private final BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String infoBateria = intent.getStringExtra(BatteryInfoReceiver.EXTRA_BATTERY_INFO);
            estadoBateriaTexto.setText(infoBateria); // Actualiza el TextView con la información de la batería
            Log.d(TAG, "Información de batería recibida: " + infoBateria);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        estadoBateriaTexto = findViewById(R.id.textView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Registrar el BroadcastReceiver en onResume
        IntentFilter intentFilter = new IntentFilter("org.idnp.samplebroadcastreceptor.ACTION_BATTERY_INFO");
        registerReceiver(batteryInfoReceiver, intentFilter);
        Log.d(TAG, "BatteryInfoReceiver registrado satisfactoriamente");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Desregistrar el BroadcastReceiver en onPause
        unregisterReceiver(batteryInfoReceiver);
        Log.d(TAG, "BatteryInfoReceiver desregistrado satisfactoriamente");
    }
}
