package com.dastan.homework61.data;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.dastan.homework61.data.ApiEndpoint.LATEST_CURRENCY;

public interface CurrencyService {

    @GET(LATEST_CURRENCY)
    Call<JsonObject> latestCurrency(@Query("access_key") String key);
}
