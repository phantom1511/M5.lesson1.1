package com.dastan.m5lesson11.ui.splash;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.dastan.m5lesson11.R;
import com.dastan.m5lesson11.data.PreferenceHelper;
import com.dastan.m5lesson11.ui.main.MainActivity;
import com.dastan.m5lesson11.ui.onboard.OnBoardActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                selectActivity();
            }
        }, 2_000);

    }

    private void selectActivity() {
        if (PreferenceHelper.getIsFirstLaunch()) {
            MainActivity.start(this);
        } else {
            PreferenceHelper.setIsFirstLaunch();
            OnBoardActivity.start(this);
        }
        finish();
    }
}
