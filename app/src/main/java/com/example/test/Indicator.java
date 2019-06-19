package com.example.test;

import android.widget.ImageView;

public class Indicator {
    private ImageView color;
    private String id;
    private long light;

    public Indicator() {
        // default constructor
    }

    public Indicator(ImageView color, String id, long light) {
        this.color = color;
        this.id = id;
        this.light = light;
    }

    public ImageView getColor() {
        return color;
    }

    public void setColor(ImageView color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLight() {
        return light;
    }

    public void setLight(long light) {
        this.light = light;
    }
}
