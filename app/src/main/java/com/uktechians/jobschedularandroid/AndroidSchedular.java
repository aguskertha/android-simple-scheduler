package com.uktechians.jobschedularandroid;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.uktechians.jobschedularandroid.API.APIMessage;
import com.uktechians.jobschedularandroid.Listener.CreateSensorRecordListener;
import com.uktechians.jobschedularandroid.Models.SensorRequest;
import com.uktechians.jobschedularandroid.Repository.SensorRepository;

import java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class AndroidSchedular extends JobService implements CreateSensorRecordListener {
    @Override
    public boolean onStartJob(JobParameters params) {
        startJob(params);
        return false;
    }


    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    private void startJob(JobParameters params) {
        Random random = new Random();
        int oxi =  random.nextInt(200 - 0) + 0;
        int heart =  random.nextInt(200 - 0) + 0;

        SensorRequest sensorRequest = new SensorRequest(oxi, heart);
        SensorRepository.createSensorRecord(sensorRequest, this::onCreateSensorRecord);

    }

    @Override
    public void onCreateSensorRecord(APIMessage message) {
        Log.d("JobScheduler", message.getMessage());
    }
}
