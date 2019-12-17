package com.dastan.m5lesson21;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dastan.m5lesson21.base.BaseActivity;
import com.dastan.m5lesson21.data.RetrofitBuilder;
import com.dastan.m5lesson21.data.entity.CurrentWeather;
import com.dastan.m5lesson21.data.entity.ForecastEntity;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dastan.m5lesson21.BuildConfig.API_KEY;

public class MainActivity extends BaseActivity {

    @BindView(R.id.textViewCity)
    TextView textViewWeather;
    @BindView(R.id.forcastView)
    TextView forecastViewWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchWeather();
    }

    @OnClick(R.id.textViewCity)
    public void onClickMethod(View view){

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initViews(){
        textViewWeather = findViewById(R.id.textViewCity);
    }

    private void fetchWeather(){
        RetrofitBuilder.getService().currentWeather("Bishkek",
                API_KEY, "metric").enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response.isSuccessful() && response.body() != null){
                    fillDate(response.body());
                    Toast.makeText(getApplicationContext(), "Temperature now", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                toast(t.getLocalizedMessage());
            }
        });

        RetrofitBuilder.getService().forecastWeather("Bishkek",
                API_KEY, "metric").enqueue(new Callback<ForecastEntity>() {
            @Override
            public void onResponse(Call<ForecastEntity> call, Response<ForecastEntity> response) {
                forecastViewWatcher.setText(response.body().getList().get(0).getMain().getTemp().toString());
                toast("Forecast Weather");
            }

            @Override
            public void onFailure(Call<ForecastEntity> call, Throwable t) {

            }
        });
    }

    private void fillDate(CurrentWeather weather){
        textViewWeather.setText(weather.getMain().getTemp().toString());
        //replaceFragment(R.id.container, new Fragment());
    }


}
