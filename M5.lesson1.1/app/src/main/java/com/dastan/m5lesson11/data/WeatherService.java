package com.dastan.m5lesson11.data;

import com.dastan.m5lesson11.data.entity.CurrentWeather;
import com.dastan.m5lesson11.data.entity.ForecastEntity;
import com.dastan.m5lesson11.data.entity.Main;
import com.dastan.m5lesson11.data.entity.Wind;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.dastan.m5lesson11.data.ApiEndpoints.CURRENT_WEATHER;
import static com.dastan.m5lesson11.data.ApiEndpoints.FORECAST;

public interface WeatherService {

    @GET(CURRENT_WEATHER)
    Call<CurrentWeather> currentWeather(@Query("q") String city, @Query("appid") String key, @Query("units") String c);

    @GET(FORECAST)
    Call<ForecastEntity> forecastWeather(@Query("q") String city, @Query("appid") String key, @Query("units") String c);
}
