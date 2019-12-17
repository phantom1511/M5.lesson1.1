package com.dastan.m5lesson21.data;

import com.dastan.m5lesson21.data.entity.CurrentWeather;
import com.dastan.m5lesson21.data.entity.ForecastEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.dastan.m5lesson21.data.ApiEndpoints.CURRENT_FORECAST;
import static com.dastan.m5lesson21.data.ApiEndpoints.CURRENT_WEATHER;

public interface WeatherService {

    @GET(CURRENT_WEATHER)
    Call<CurrentWeather> currentWeather(@Query("q") String city, @Query("appid") String key, @Query("units") String c);

    @GET(CURRENT_FORECAST)
    Call<ForecastEntity> forecastWeather(@Query("q") String city, @Query("appid") String key, @Query("units") String c);
}
