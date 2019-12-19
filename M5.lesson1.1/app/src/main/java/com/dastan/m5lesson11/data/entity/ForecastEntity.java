package com.dastan.m5lesson11.data.entity;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ForecastEntity {

    private List<CurrentWeather> list;


    public List<CurrentWeather> getList(){
        return list;
    }

    public void setList(List<CurrentWeather> list) {
        this.list = list;
    }
}
