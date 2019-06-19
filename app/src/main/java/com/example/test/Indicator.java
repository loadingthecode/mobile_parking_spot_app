package com.example.test;

import android.widget.ImageView;

public class Indicator {
    private ImageView status;
    private int color;
    private String id;

    private long light;



    public Indicator() {
        // default constructor
        this.color = 0;
        this.id = "";
        this.light = 0;
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

    public ImageView getStatus() {
        return status;
    }

    public void setStatus(ImageView status) {
        this.status = status;
    }
}
