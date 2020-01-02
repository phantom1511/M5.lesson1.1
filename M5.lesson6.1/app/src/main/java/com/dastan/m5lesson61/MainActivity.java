package com.dastan.m5lesson61;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dastan.m5lesson61.data.RequestCurrencies;

public class MainActivity extends AppCompatActivity {

    private String URL = "http://data.fixer.io/api/latest?access_key=8a85eeadb3af245c2bda640eb6e5afa8";
    //private Spinner spinner;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RequestCurrencies().execute(URL);

        editText = findViewById(R.id.editTextInput);
        textView = findViewById(R.id.textViewShow);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textView.setText(s.toString());
            }
        });

//        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, ratesTitles);
//        spinner.setAdapter(arrayAdapter);
    }
}
