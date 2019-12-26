package com.dastan.m5lesson11.ui.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.dastan.m5lesson11.R;
import com.dastan.m5lesson11.base.BaseMapActivity;
import com.dastan.m5lesson11.service.ForegroundService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import butterknife.BindView;

public class MapActivity extends BaseMapActivity {

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private boolean btn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        floatingActionButton.setOnClickListener(v -> {
            if (btn == false) {
                btn = true;
                Intent intent = new Intent(getApplicationContext(), ForegroundService.class);
                startService(intent);
                floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_black_24dp));
            } else {
                Intent intent = new Intent(getApplicationContext(), ForegroundService.class);
                stopService(intent);
                btn = false;
                floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_start));
            }
        });
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        super.onMapReady(mapboxMap);

        mapboxMap.addOnMapLongClickListener(point -> {
            toast("coordinates");
            AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
            builder.setTitle("Are you sure with your coordinates? ")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        Intent intent = new Intent();
                        intent.putExtra("lat", point.getLatitude());
                        intent.putExtra("lng", point.getLongitude());
                        setResult(RESULT_OK, intent);
                        finish();
                    }).setNegativeButton("No", (dialog, which) -> dialog.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return true;
        });
    }
}
