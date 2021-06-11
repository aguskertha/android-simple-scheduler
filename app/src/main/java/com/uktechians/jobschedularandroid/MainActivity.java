package com.uktechians.jobschedularandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRes();
    }

    private void initRes() {
        start = findViewById(R.id.btn_start);
        stop = findViewById(R.id.btn_stop);

        // add listener
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_start){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                JobScheduler jobScheduler = getSystemService(JobScheduler.class);
                ComponentName componentName = new ComponentName(this, AndroidSchedular.class);
                JobInfo.Builder info = new JobInfo.Builder(123, componentName);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    info.setRequiresBatteryNotLow(true);
                }
                info.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);
                info.setPeriodic(15*60*1000);
                if (jobScheduler!=null){
                    int result = jobScheduler.schedule(info.build());
                    if (result==JobScheduler.RESULT_SUCCESS){
                        Toast.makeText(this, "JOB STARTED", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this, "FAILED TO START JOB", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }
        else if (v.getId()==R.id.btn_stop){
            JobScheduler jobScheduler = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                jobScheduler = getSystemService(JobScheduler.class);
                jobScheduler.cancel(123);
            }

        }
    }
}