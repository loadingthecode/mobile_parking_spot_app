package com.example.test;

public class Sensor {
    private String id;
    private double light;

    public Sensor() {

    }

    public Sensor(String id, double light) {
        this.id = id;
        this.light = light;
    }

    public String getSensorId() {
        return id;
    }

    public double getSensorLight() {
        return light;
    }


}
