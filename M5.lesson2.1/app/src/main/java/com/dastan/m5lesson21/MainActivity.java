package com.dastan.m5lesson21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.dastan.m5lesson21.data.RetrofitBuilder;
import com.dastan.m5lesson21.data.entity.CurrentWeather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView cityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchWeather();
        initViews();
    }

    private void initViews(){
        cityText = findViewById(R.id.textViewCity);
    }

    private void fetchWeather(){
        RetrofitBuilder.getService().currentWeather("Bishkek",
                getResources().getString(R.string.weather_key), "metric").enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response.isSuccessful() && response.body() != null){
                    cityText.setText(response.body().getMain().getTemp().toString());
                    Toast.makeText(getApplicationContext(), "Temperature now", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
