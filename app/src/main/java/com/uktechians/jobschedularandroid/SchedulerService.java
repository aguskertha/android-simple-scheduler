package com.uktechians.jobschedularandroid;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class SchedulerService {
    public static void startScheduler(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
            ComponentName componentName = new ComponentName(context, AndroidSchedular.class);
            JobInfo.Builder info = new JobInfo.Builder(123, componentName);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                info.setRequiresBatteryNotLow(true);
            }
            info.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);
            info.setPeriodic(15*60*1000);
            if (jobScheduler!=null){
                int result = jobScheduler.schedule(info.build());
                if (result==JobScheduler.RESULT_SUCCESS){
                    Toast.makeText(context, "JOB STARTED", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "FAILED TO START JOB", Toast.LENGTH_SHORT).show();

                }
            }
            else {
                Log.d("Service", "Scheduler Stop");
            }
        }
    }

    public static void stopScheduler(Context context){
        JobScheduler jobScheduler = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            jobScheduler = context.getSystemService(JobScheduler.class);
            jobScheduler.cancel(123);
            Log.d("Service", "Scheduler Stop");
        }
    }

}
