package com.uktechians.jobschedularandroid.API;

import com.uktechians.jobschedularandroid.Models.SensorRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIService {
    @POST("sensor")
    Call<APIMessage> createSensorRecord(@Body SensorRequest sensorRequest);
}
