package com.example.test;

import android.widget.ImageView;
import android.widget.TextView;

public class Indicator {
    private ImageView status;
    private TextView lightDebug;
    private String id;
    private long light;

    public Indicator() {
        // default constructor
        this.id = "";
        this.light = 0;
    }

    public Indicator(String id, long light) {
        this.id = id;
        this.light = light;
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

    public TextView getLightDebug() {
        return lightDebug;
    }

    public void setLightDebug(TextView lightDebug) {
        this.lightDebug = lightDebug;
    }
}
