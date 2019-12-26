package com.dastan.m5lesson11.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.dastan.m5lesson11.data.NotificationHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;

public class ForegroundService extends Service {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private ArrayList<Location> coordinates = new ArrayList<>();;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (intent.getAction().equals("Stop")) {
//            stopSelf();
//        } else {
            startForeground(1, NotificationHelper.createNotification(this, "Title", "Context"));
            callPermission();


        return START_STICKY;
    }

    private void requestLocationUpdate() {
        fusedLocationProviderClient = new FusedLocationProviderClient(this);

        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(2000);
        locationRequest.setInterval(4000);

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                coordinates.add(locationResult.getLastLocation());
                Log.e("ron", "lat: " + locationResult.getLastLocation().getLatitude()
                        + " log: " + locationResult.getLastLocation().getLongitude() + " " + coordinates.size());
            }
        }, getMainLooper());
    }

    private void callPermission() {
        Permissions.check(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                "Location permission needed to run this app", new Permissions.Options()
                        .setSettingsDialogTitle("Warning!").setRationaleDialogTitle("Info"),
                new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        requestLocationUpdate();
                    }

                    @Override
                    public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                        super.onDenied(context, deniedPermissions);
                        callPermission();
                    }
                });
    }
}
