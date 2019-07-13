package com.example.doreopartners.fieldmappingtge;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;

import java.util.HashMap;

public class BackgroundSync extends JobService {


    //SyncData.SendAppVersion sendAppVersion;
    @SuppressLint("StaticFieldLeak")

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        HashMap<String,String> user = sessionManagement.getUserDetails();
        String staff_id = user.get(SessionManagement.KEY_STAFF_ID);

       SendAppVersion sendAppVersion = new SendAppVersion(getApplicationContext()){};
       sendAppVersion.execute(BuildConfig.APPLICATION_ID,BuildConfig.VERSION_NAME,staff_id);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        //downloadApplication.cancel(true);
        return false;
    }
}