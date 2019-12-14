package com.dastan.m5lesson11.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static WeatherService weatherService;

    public static WeatherService getWeatherService(){
        if (weatherService == null){
            weatherService = buildRetrofit();
        }
        return weatherService;
    }

    private static WeatherService buildRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService.class);
    }
}
