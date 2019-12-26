package com.dastan.m5lesson51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dastan.m5lesson51.data.NotificationHelper;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.BtnNotification);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationHelper.createNotification(getApplicationContext(), "Title", "Body");
            }
        });
    }

    public void onStart(View view) {
        Intent intent = new Intent(getApplicationContext(), ForegroundService.class);
        startService(intent);
    }

    public void onStop(View view) {
        Intent intent = new Intent(getApplicationContext(), ForegroundService.class);
        stopService(intent);
    }
}
