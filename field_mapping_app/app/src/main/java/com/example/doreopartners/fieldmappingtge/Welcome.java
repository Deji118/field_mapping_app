package com.example.doreopartners.fieldmappingtge;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//THIS PAGE RETRIEVES FIELD DETAILS BEFORE TRACKING AND PRESENTS TRACKING MODE
public class Welcome extends AppCompatActivity {
    SessionManagement sessionManagement;
    SharedPreferences member;
    SharedPreferences prefs;
    TextView txtName;
    Button btnForm;
    SharedPreferences.Editor memEdit;
    String staff_name;
    String staff_role;
    String staff_id;
    //String mem_id;
    String member_id;
    String TFMuniqueid;

    String first_name;
    String last_name;
    String counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtName = findViewById(R.id.txtName);

        member = getSharedPreferences("member", MODE_PRIVATE);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        btnForm = findViewById(R.id.btnForm);
        memEdit = member.edit();
        //mem_id =getIntent().getStringExtra("member_id");

        //TODO 1. your intent that launches my app starts here and initializes these shared preference variables
        Intent intent = getIntent();


        member.getString("staff_id","001");
        member_id=getIntent().getStringExtra("member_id");
        first_name=getIntent().getStringExtra("first_name");
        last_name=getIntent().getStringExtra("last_name");
        counter=getIntent().getStringExtra("counter");


//        Log.d("member_id",member_id);
        //      Log.d("first_name",firstname);
        //String member_id= getIntent().getStringExtra("member_id");
        OnlineDBHelper db=new OnlineDBHelper(this);
        String crop_type=db.getcroptype(member_id);
        memEdit.putString("crop_type",crop_type);
        TFMuniqueid=getIntent().getStringExtra("TFMuniqueid");
//        Log.d("tfmmm",TFMuniqueid);

        memEdit.commit();

        //Log.d("crop_type",crop_type);




        txtName.setText(first_name+" "+last_name);
        if (member_id==null)
        {
            member_id=member.getString("member_id","001");
            first_name=member.getString("first_name","001");
            last_name=member.getString("last_name","001");
            counter=member.getString("counter","001");
            txtName.setText(first_name+" "+last_name);
            TFMuniqueid=member.getString("TFMuniqueid","111");
            Log.d("tfmmm",TFMuniqueid);
        }
        else
        {
            memEdit.putString("member_id",member_id);
            memEdit.putString("last_name",last_name);
            memEdit.putString("first_name",first_name);
            memEdit.putString("counter",counter);
            memEdit.putString("TFMuniqueid",TFMuniqueid);
            Log.d("tfmmm",TFMuniqueid);
            memEdit.commit();
        }


    }

//    public void form(View v) {
//        if (prefs.getBoolean("mapped", false)) {
//            startActivity(new Intent(Welcome.this, MappingForm.class));
//        } else {
//            Toast.makeText(getApplicationContext(), "Please map a field before filling the form", Toast.LENGTH_LONG).show();
//        }
//
//    }

    public void map(View v) {
        if (permissionGranted()) {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            boolean gps_enabled = false;
            boolean network_enabled = false;

            try {
                gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            } catch (Exception ex) {
            }

            try {
                network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            } catch (Exception ex) {
            }

            if (!gps_enabled && !network_enabled) {
                // notify user
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("Enable location");
                dialog.setPositiveButton("Turn on location", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);

                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
                dialog.show();
            }
            if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("Select Mapping mode");
                dialog.setPositiveButton("Walking", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        memEdit.putString("walkorbike", "W");
                        memEdit.commit();
                        startActivity(new Intent(Welcome.this, MappingActivity.class));
                    }
                });
                dialog.setNegativeButton("Bike", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        memEdit.putString("walkorbike", "B");
                        memEdit.commit();
                        startActivity(new Intent(Welcome.this, MappingActivity.class));
                    }
                });
                dialog.show();

            }
            else
            {
                Toast.makeText(this,"Change your location settings to use GPS",Toast.LENGTH_SHORT);
            }
        } else {
            ActivityCompat.requestPermissions(Welcome.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

    }

    public boolean permissionGranted() {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Welcome.this, Main2Activity.class));
        finish();

    }

}
