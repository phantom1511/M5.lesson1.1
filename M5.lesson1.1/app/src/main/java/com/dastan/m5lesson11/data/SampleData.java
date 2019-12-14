package com.dastan.m5lesson11.data;

import com.dastan.m5lesson11.R;

import java.util.ArrayList;

public class SampleData {
    String title;
    int img;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int [] sliderImages = {
            R.drawable.cris1,
            R.drawable.cris2,
            R.drawable.cris3
    };

    public String[] sliderHeadings = {
            "Cristiano Ronaldo",
            "Dos Santos",
            "Aveiro"
    };
}

