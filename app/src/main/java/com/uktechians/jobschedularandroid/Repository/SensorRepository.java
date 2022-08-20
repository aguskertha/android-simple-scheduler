package com.uktechians.jobschedularandroid.Repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uktechians.jobschedularandroid.API.APIConfig;
import com.uktechians.jobschedularandroid.API.APIMessage;
import com.uktechians.jobschedularandroid.Listener.CreateSensorRecordListener;
import com.uktechians.jobschedularandroid.Models.SensorRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SensorRepository {
    public static final void createSensorRecord(SensorRequest sensorRequest, CreateSensorRecordListener createSensorRecordListener){
        try {
            Call<APIMessage> callCreateSensorRecord = APIConfig.getService().createSensorRecord(sensorRequest);
            callCreateSensorRecord.enqueue(new Callback<APIMessage>() {
                @Override
                public void onResponse(Call<APIMessage> call, Response<APIMessage> response) {
                    if(response.isSuccessful()){
                        APIMessage message = response.body();
                        createSensorRecordListener.onCreateSensorRecord(message);
                    }
                    else if(response.code() == 400){
                        APIMessage message = new Gson().fromJson(response.errorBody().charStream(), APIMessage.class);
                        Log.d("Repository", message.getMessage());
                    }
                    else {
                        Log.d("Repository", "Server Error!");
                    }
                }

                @Override
                public void onFailure(Call<APIMessage> call, Throwable t) {

                }
            });
        }
        catch (Exception err){
            Log.d("Repository", err.toString());
        }
    }
}
