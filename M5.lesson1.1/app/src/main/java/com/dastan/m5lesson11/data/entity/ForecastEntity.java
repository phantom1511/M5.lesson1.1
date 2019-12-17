package com.dastan.m5lesson11.data.entity;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ForecastEntity {

    private List<CurrentWeather> list;

    private String weekWeatherIcon;
    private String weekDay;
    private String maxTemp;
    private String minTemp;

    public ForecastEntity(ImageView weatherWeekIcon, TextView dayWeekText, TextView maxTempWeek, TextView minTempWeek) {
    }

    public String getWeekWeatherIcon() {
        return weekWeatherIcon;
    }

    public void setWeekWeatherIcon(String weekWeatherIcon) {
        this.weekWeatherIcon = weekWeatherIcon;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public List<CurrentWeather> getList(){
        return list;
    }

    public void setList(List<CurrentWeather> list) {
        this.list = list;
    }
}
