package com.dastan.m5lesson11;

import android.app.Application;

import com.dastan.m5lesson11.data.PreferenceHelper;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceHelper.init(this);
    }
}
