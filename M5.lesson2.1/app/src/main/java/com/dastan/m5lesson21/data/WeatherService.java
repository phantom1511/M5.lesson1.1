package com.dastan.m5lesson21.data;

import com.dastan.m5lesson21.data.entity.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather")
    Call<CurrentWeather> currentWeather(@Query("q") String city, @Query("appid") String key, @Query("units") String c);
}
