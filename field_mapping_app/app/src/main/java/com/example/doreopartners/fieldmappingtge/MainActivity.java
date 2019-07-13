package com.example.doreopartners.fieldmappingtge;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
//THIS IS THE LANDING PAGE FOR THE APP, START FROM HERE AND ENJOY
public class MainActivity extends AppCompatActivity {

    //private static final String TAG = "MainActivity";
    String member_ID;
    //EditText mem;
    SessionManagement sessionManagement;
    String staff_name;
    String staff_role;
    String staff_id;
    SharedPreferences member;
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEdit;

    private JobScheduler jobScheduler;
    private JobInfo jobInfo;
    ComponentName componentName;
    private static final int JOB_ID =101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        member = getSharedPreferences("member", MODE_PRIVATE);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        prefsEdit = member.edit();
        //String name=mPreferences.getString("staff_id","default");
        //Log.d(TAG,"onCreate");
        OnlineDBHelper db=new OnlineDBHelper(this);
        executeService();

    }

    public void open(View view) {

        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            //Intent intent1 =new Intent(Intent.ACTION_MAIN);
            intent.setComponent(new ComponentName("com.babbangona.bgstore", "com.babbangona.bgstore.MainActivity"));
            new Intent(getApplicationContext(), startmapping.class);
            startActivity(intent);

            //Intent fp=new Intent(getApplicationContext(),startmapping.class);
            //startActivity(fp);
        } catch (Exception e) {

            //Log.d("result", e);
            Toast.makeText(this, "You have not installed BG store", Toast.LENGTH_SHORT).show();
        }

//    }    public void GoToRegisterPage (View view) {
//
////        if(mem.getText().toString().trim().equalsIgnoreCase("")){
////            Toast.makeText(this, "You have not typed a message", Toast.LENGTH_SHORT).show();
////        }else{ member_ID = mem.getText().toString();
//        Intent intent = new Intent(getApplicationContext(), Assets.class);
////        intent.putExtra("member_id",member_ID);
//        startActivity(intent);
//    }
    }


    public void onDestroy() {
        super.onDestroy();
        executeService();
    }

    public void executeService(){
        componentName = new ComponentName(this, BackgroundSync.class);
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, componentName);
        builder.setPeriodic(1000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPersisted(true);
        jobInfo = builder.build();
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);


    }
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}