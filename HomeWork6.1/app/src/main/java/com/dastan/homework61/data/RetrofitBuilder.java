package com.dastan.homework61.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static CurrencyService currencyService;

    public static CurrencyService getCurrencyService() {
        if (currencyService == null){
            currencyService = buildRetrofit();
        }

        return currencyService;
    }

    private static CurrencyService buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://data.fixer.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CurrencyService.class);
    }
}
