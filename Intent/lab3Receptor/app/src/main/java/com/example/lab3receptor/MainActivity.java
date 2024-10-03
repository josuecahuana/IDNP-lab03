package com.example.lab3receptor;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "receptorBroadcast";
    private TextView estadoBateriaTexto;
    private MoonBroadcastReceiver moonBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Actividad creada");

        estadoBateriaTexto = findViewById(R.id.textView);

        moonBroadcastReceiver = new MoonBroadcastReceiver(estadoBateriaTexto);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Actividad iniciada");
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("org.idnp.samplebroadcastreceptor.ACTION_MOON_PHASE");
        registerReceiver(moonBroadcastReceiver, intentFilter);
        Log.d(TAG, "onResume: BroadcastReceiver registrado satisfactoriamente");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(moonBroadcastReceiver);
        Log.d(TAG, "onPause: BroadcastReceiver desregistrado satisfactoriamente");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: Actividad detenida");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Actividad destruida");
    }
}
