package com.example.test;

public class Day {
    private String day;

    private float earlyMorning;
    private float lateMorning;
    private float earlyAfternoon;
    private float lateAfternoon;

    public Day() {

    }

    public Day(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public float getEarlyMorning() {
        return earlyMorning;
    }

    public void setEarlyMorning(float earlyMorning) {
        this.earlyMorning = earlyMorning;
    }

    public float getLateMorning() {
        return lateMorning;
    }

    public void setLateMorning(float lateMorning) {
        this.lateMorning = lateMorning;
    }

    public float getEarlyAfternoon() {
        return earlyAfternoon;
    }

    public void setEarlyAfternoon(float earlyAfternoon) {
        this.earlyAfternoon = earlyAfternoon;
    }

    public float getLateAfternoon() {
        return lateAfternoon;
    }

    public void setLateAfternoon(float lateAfternoon) {
        this.lateAfternoon = lateAfternoon;
    }

    public void setAllTimes(float earlyMorning, float lateMorning,
                            float earlyAfternoon, float lateAfternoon) {
        this.earlyMorning = earlyMorning;
        this.lateMorning = lateMorning;
        this.earlyAfternoon = earlyAfternoon;
        this.lateAfternoon = lateAfternoon;
    }
}
