package com.dastan.m5lesson11.data;

import android.widget.ImageView;
import android.widget.TextView;

public class ListView {
    private String weekWeatherIcon;
    private String weekDay;
    private String maxTemp;
    private String minTemp;


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
}
