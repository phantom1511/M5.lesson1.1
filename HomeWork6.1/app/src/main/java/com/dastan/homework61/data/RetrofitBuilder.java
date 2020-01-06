package com.dastan.homework61.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dastan.homework61.BuildConfig.BASE_URL;

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
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CurrencyService.class);
    }
}
