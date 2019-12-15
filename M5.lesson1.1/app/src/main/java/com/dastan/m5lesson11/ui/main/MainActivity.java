package com.dastan.m5lesson11.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dastan.m5lesson11.R;
import com.dastan.m5lesson11.data.RetrofitBuilder;
import com.dastan.m5lesson11.data.entity.CurrentWeather;
import com.dastan.m5lesson11.data.entity.Main;
import com.dastan.m5lesson11.data.entity.Wind;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tempCity, humidity, pressure, tempMax,
            tempMin, nameCity, wind, clouds, day, month, year, sunrise, sunset;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        fetchWeather();
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private void initViews() {
        tempCity = findViewById(R.id.cityTemp);
        humidity = findViewById(R.id.humidityView);
        pressure = findViewById(R.id.pressureView);
        tempMax = findViewById(R.id.maxTemp);
        tempMin = findViewById(R.id.minTemp);
        nameCity = findViewById(R.id.cityName);
        wind = findViewById(R.id.windView);
        imageView = findViewById(R.id.weatherImg);
        clouds = findViewById(R.id.cloudView);
        day = findViewById(R.id.day);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);
        sunrise = findViewById(R.id.sunriseView);
        sunset = findViewById(R.id.sunsetView);
    }

    private void fetchWeather() {
        RetrofitBuilder.getWeatherService().currentWeather("Bishkek",
                getResources().getString(R.string.weather_key),
                "metric").enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tempCity.setText(response.body().getMain().getTemp().toString() + " °C");
                    tempMax.setText(response.body().getMain().getTempMax().toString() + " °C");
                    tempMin.setText(response.body().getMain().getTempMin().toString() + " °C");
                    humidity.setText(response.body().getMain().getHumidity().toString() + "%");
                    pressure.setText(response.body().getMain().getPressure().toString() + "mb");
                    clouds.setText(response.body().getClouds().getAll().toString() + "%");
                    wind.setText(response.body().getWind().getSpeed().toString() + "m/s");
                    nameCity.setText(response.body().getName() + " " + response.body().getSys().getCountry());
//                    sunrise.setText(response.body().getSys().getSunrise().toString());
//                    sunset.setText(response.body().getSys().getSunset().toString());
//                    day.setText(response.raw().headers().getDate("Date").toString());
                    Glide.with(getApplicationContext())
                            .load("http://openweathermap.org/img/wn/"
                            + response.body().getWeather().get(0).getIcon() + "@2x.png")
                            .into(imageView);
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
