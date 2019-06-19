package com.example.test;

import android.widget.ImageView;

public class Indicator {
    private int color;
    private String id;
    private long light;

    public Indicator() {
        // default constructor
    }

    public Indicator(int color, String id, long light) {
        this.color = color;
        this.id = id;
        this.light = light;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
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
