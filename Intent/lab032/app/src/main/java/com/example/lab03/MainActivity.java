package com.example.lab03;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Emisor";
    public static final String EXTRA_MOON_PHASE = "org.idnp.sampleintentbroadcatreceiver.MoonBroadcastReceiver.EXTRA_MOON_PHASE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSendMessage = findViewById(R.id.btnSendMessage);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = getBatteryDetails();
                sendMessage(message);
                Log.d(TAG, "Mensaje enviado: " + message);
            }
        });
    }

    public void sendMessage(String message) {
        Intent intent = new Intent();
        intent.setAction("org.idnp.samplebroadcastreceptor.ACTION_MOON_PHASE");
        intent.putExtra(EXTRA_MOON_PHASE, message);
        sendBroadcast(intent);
        Log.d(TAG, "Mensaje enviado: " + message);
    }

    private String getBatteryDetails() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent estadoBateria = registerReceiver(null, ifilter);

        int level = estadoBateria != null ? estadoBateria.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = estadoBateria != null ? estadoBateria.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;
        int status = estadoBateria != null ? estadoBateria.getIntExtra(BatteryManager.EXTRA_STATUS, -1) : -1;

        float batteryPct = (level / (float) scale) * 100;

        String statusString;
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusString = "Cargando";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusString = "Descargando";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusString = "Batería llena";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                statusString = "No está cargando";
                break;
            default:
                statusString = "Estado desconocido";
                break;
        }

        return String.format("Porcentaje: %.5f%%, Estado: %s", batteryPct, statusString);
    }
}
