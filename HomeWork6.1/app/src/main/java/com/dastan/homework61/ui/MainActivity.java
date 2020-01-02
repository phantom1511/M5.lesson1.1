package com.dastan.homework61.ui;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.dastan.homework61.R;
import com.dastan.homework61.base.BaseActivity;
import com.dastan.homework61.data.RetrofitBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dastan.homework61.BuildConfig.API_KEY;

public class MainActivity extends BaseActivity {

    @BindView(R.id.inputText)
    EditText editInput;
    @BindView(R.id.resultView)
    TextView textResult;
    @BindView(R.id.fromSpinner)
    Spinner fromSpinner;
    @BindView(R.id.toSpinner)
    Spinner toSpinner;

    ArrayList<String> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initListener();
        spinner();
        fetchCurrency();
    }

    private void initListener() {
        editInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textResult.setText(s.toString());
            }
        });
    }

    private void spinner() {
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.animals, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(arrayAdapter);
        toSpinner.setAdapter(arrayAdapter);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, ratesTitles);
//        fromSpinner.setAdapter(arrayAdapter);

    }

    private void fetchCurrency(){
        RetrofitBuilder.getCurrencyService().latestCurrency(API_KEY).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null){
                    setResponse(response);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void setResponse(Response<JsonObject> response) {
        if (response.body() != null) {
            response.body().getAsJsonObject("rates");
        }
    }
}
