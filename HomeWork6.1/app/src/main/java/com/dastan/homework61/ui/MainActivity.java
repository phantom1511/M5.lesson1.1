package com.dastan.homework61.ui;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.dastan.homework61.R;
import com.dastan.homework61.base.BaseActivity;
import com.dastan.homework61.data.RetrofitBuilder;
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

    private double firstCurrencyTitle, secondCurrencyTitle;
    private Object[] ratesTitles;
    private ArrayList<String> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initListener();
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
                if (s != null && s.length() > 0) {
                    textResult.setText(calculate(String.valueOf(s), firstCurrencyTitle, secondCurrencyTitle));
                } else {
                    textResult.setText("");
                }

            }
        });

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                firstCurrencyTitle = Double.parseDouble(list.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                firstCurrencyTitle = Double.parseDouble(list.get(0));
            }
        });

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                secondCurrencyTitle = Double.parseDouble(list.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                secondCurrencyTitle = Double.parseDouble(list.get(0));
            }
        });
    }

//    private void spinner() {
//        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.animals, android.R.layout.simple_spinner_item);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        fromSpinner.setAdapter(arrayAdapter);
//        toSpinner.setAdapter(arrayAdapter);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, ratesTitles);
//        fromSpinner.setAdapter(arrayAdapter);
//
//    }

    private void fetchCurrency() {
        RetrofitBuilder.getCurrencyService().latestCurrency(API_KEY).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject object = response.body();
                    JsonObject rates = object.getAsJsonObject("rates");
                    ratesTitles = rates.keySet().toArray();
                    list = new ArrayList<>();
                    for (Object ratesTitles : ratesTitles) {
                        list.add(String.valueOf(rates.getAsJsonPrimitive(ratesTitles.toString())));
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, ratesTitles);


                    fromSpinner.setAdapter(arrayAdapter);
                    toSpinner.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                toast(t.getLocalizedMessage());
            }
        });
    }

    private String calculate(String values, double firstCurrency, double secondCurrency) {
        double result = ((Double.parseDouble(values) / firstCurrency) * secondCurrency);
        toast("currency right now");
        return String.valueOf(result);
    }

//    private void setResponse(Response<JsonObject> response) {
//        if (response.body() != null) {
//            response.body().getAsJsonObject("rates");
//        }
//    }
}
