package com.dastan.m5lesson11.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dastan.m5lesson11.R;
import com.dastan.m5lesson11.base.BaseActivity;
import com.dastan.m5lesson11.data.RetrofitBuilder;
import com.dastan.m5lesson11.data.entity.CurrentWeather;
import com.dastan.m5lesson11.data.entity.ForecastEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dastan.m5lesson11.BuildConfig.API_KEY;

public class MainActivity extends BaseActivity {

    @BindView(R.id.cityTemp)
    TextView tempCity;
    @BindView(R.id.humidityView)
    TextView humidity;
    @BindView(R.id.pressureView)
    TextView pressure;
    @BindView(R.id.maxTemp)
    TextView tempMax;
    @BindView(R.id.minTemp)
    TextView tempMin;
    @BindView(R.id.cityName)
    TextView nameCity;
    @BindView(R.id.windView)
    TextView wind;
    @BindView(R.id.weatherImg)
    ImageView imageView;
    @BindView(R.id.cloudView)
    TextView clouds;
    @BindView(R.id.day)
    TextView day;
    @BindView(R.id.sunriseView)
    TextView sunrise;
    @BindView(R.id.sunsetView)
    TextView sunset;
    @BindView(R.id.statusView)
    TextView status;

    @BindView(R.id.weekRecyclerView)
    RecyclerView recyclerViewWeek;
    private ForecastEntity weekData;
    private WeatherWeekAdapter weekAdapter;
    private double lat;
    private double lon;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fetchWeather();
        fetchForecastWeather();
        initViews();
    }

    private void initViews(){
        recyclerViewWeek.setHasFixedSize(true);
        recyclerViewWeek.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

//        weekData = new ForecastEntity(weatherWeekIcon, dayWeekText, maxTempWeek, minTempWeek);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private void fetchWeather() {
        RetrofitBuilder.getWeatherService().currentWeather("Bishkek",
                API_KEY,
                "metric").enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response.isSuccessful() && response.body() != null) {
                    setResponse(response);
                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                toast(t.getLocalizedMessage());
            }
        });
    }

    private void fetchForecastWeather() {
        RetrofitBuilder.getWeatherService().forecastWeather("Bishkek", API_KEY, "metric")
                .enqueue(new Callback<ForecastEntity>() {
                    @Override
                    public void onResponse(Call<ForecastEntity> call, Response<ForecastEntity> response) {
                        weekAdapter = new WeatherWeekAdapter(response.body().getList());
                        recyclerViewWeek.setAdapter(weekAdapter);
                    }

                    @Override
                    public void onFailure(Call<ForecastEntity> call, Throwable t) {
                        toast(t.getLocalizedMessage());
                    }
                });
    }

    private String parseDateToTime(double d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = new Date();
        date.setTime((long) d * 1000);
        return dateFormat.format(date.getTime());
    }

    public void setResponse(Response<CurrentWeather> response) {
        tempCity.setText(response.body().getMain().getTemp().toString() + " °C");
        tempMax.setText(response.body().getMain().getTempMax().toString() + " °C");
        tempMin.setText(response.body().getMain().getTempMin().toString() + " °C");
        humidity.setText(response.body().getMain().getHumidity().toString() + "%");
        pressure.setText(response.body().getMain().getPressure().toString() + "mb");
        clouds.setText(response.body().getClouds().getAll().toString() + "%");
        wind.setText(response.body().getWind().getSpeed().toString() + "m/s");
        nameCity.setText(response.body().getName() + " " + response.body().getSys().getCountry());
        sunrise.setText(parseDateToTime(response.body().getSys().getSunrise()));
        sunset.setText(parseDateToTime(response.body().getSys().getSunset()));
        day.setText(new SimpleDateFormat("dd MMMM yyyy").format(new Date()));
        Glide.with(getApplicationContext())
                .load("http://openweathermap.org/img/wn/"
                        + response.body().getWeather().get(0).getIcon() + "@2x.png")
                .into(imageView);
        toast("Temperature now");
    }

    public void setMapClick(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 101 && data != null){
            lat = data.getDoubleExtra("lat", 1);
            lon = data.getDoubleExtra("lng", 1);
            getCurrentWeatherCoordinates(lat, lon);
            getForecastWeatherCoordinates(lat, lon);
        }
    }

    private void getCurrentWeatherCoordinates(double lat, double lon) {
        RetrofitBuilder.getWeatherService().coordinatesCurrentWeather(lat, lon, "metric" , API_KEY )
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        setResponse(response);
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        toast(t.getLocalizedMessage());
                    }
                });
    }

    private void getForecastWeatherCoordinates(double lat, double lon) {
        RetrofitBuilder.getWeatherService().coordinatesForecastWeather(lat, lon, "metric", API_KEY )
                .enqueue(new Callback<ForecastEntity>() {
                    @Override
                    public void onResponse(Call<ForecastEntity> call, Response<ForecastEntity> response) {
                        weekAdapter = new WeatherWeekAdapter(response.body().getList());
                        recyclerViewWeek.setAdapter(weekAdapter);
                    }

                    @Override
                    public void onFailure(Call<ForecastEntity> call, Throwable t) {
                        toast(t.getLocalizedMessage());
                    }
                });
    }
}
