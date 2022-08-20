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
            SchedulerService.startScheduler(v.getContext());
        }
        else if (v.getId()==R.id.btn_stop){
            SchedulerService.stopScheduler(v.getContext());
        }
    }
}