package com.uktechians.jobschedularandroid.Models;

public class SensorRequest {
    private int heartRate;
    private int oxiRate;

    public SensorRequest(int heartRate, int oxiRate) {
        this.heartRate = heartRate;
        this.oxiRate = oxiRate;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getOxiRate() {
        return oxiRate;
    }

    public void setOxiRate(int oxiRate) {
        this.oxiRate = oxiRate;
    }
}
