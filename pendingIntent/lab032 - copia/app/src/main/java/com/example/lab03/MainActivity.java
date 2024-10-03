package com.example.lab03;

import android.app.PendingIntent;
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
    public static final String EXTRA_BATTERY_INFO = "org.idnp.sampleintentbroadcatreceiver.BATTERY_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSendBatteryInfo = findViewById(R.id.btnSendMessage);

        btnSendBatteryInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String batteryInfo = getBatteryInfo();
                mandarInformacion(batteryInfo);
            }
        });
    }

    private String getBatteryInfo() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent estadoBateria = registerReceiver(null, ifilter);

        int level = estadoBateria != null ? estadoBateria.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = estadoBateria != null ? estadoBateria.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;

        float porcentajeBat = level / (float) scale * 100;
        return String.format("%.5f%%", porcentajeBat);
    }

    public void mandarInformacion(String batteryInfo) {
        Intent intent = new Intent();
        intent.setAction("org.idnp.samplebroadcastreceptor.ACTION_BATTERY_INFO");
        intent.putExtra(EXTRA_BATTERY_INFO, batteryInfo);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        try {
            pendingIntent.send();
            Log.d(TAG, "Información de la batería enviada usando PendingIntent: " + batteryInfo);
        } catch (PendingIntent.CanceledException e) {
            Log.e(TAG, "Error al enviar el PendingIntent: " + e.getMessage());
        }
    }
}
