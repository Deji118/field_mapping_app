package com.example.doreopartners.fieldmappingtge;
//LANDING PAGE AFTER VERIFICATION FROM ACCESS CONTROL

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class fieldselection extends AppCompatActivity {
    private static final String TAG="startmapping";
    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<modelclass> memberList1=new ArrayList<>();
    private ArrayList<Map<String,String>> memberList2;
    private ArrayList firstname;
    private ArrayList lastname;
    private ArrayList number;
    private ArrayList phonenumber;
    String lga;
    private RecyclerView.Adapter adapter;
    private RecyclerView mRecyclerView;
    String staff_name;
    String staff_role;
    String staff_id;
    String mem_id;
    SessionManagement sessionManagement;
    private RecyclerView recyclerView;
    SharedPreferences member;
    SharedPreferences prefs2;
    SharedPreferences.Editor memEdit;

    //TextView staffid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fieldselection);
        member = getSharedPreferences("member", MODE_PRIVATE);
        prefs2 = getSharedPreferences("prefs", MODE_PRIVATE);
        memEdit = member.edit();
        OnlineDBHelper db = new OnlineDBHelper(this);

        //staffid=findViewById(R.id.txtName);
        Log.d(TAG, "onCreate: started.");
        // Intent intent = getIntent();
        mem_id = member.getString("staff_id", "deji");
        //staffid.setText(mem_id);

        //String member_id=getIntent().getStringExtra("member_id");
        String lga = member.getString("lga", "deji");

        String ik_number=getIntent().getStringExtra("ik_number");
        //Toast.makeText(this, ik_number, Toast.LENGTH_SHORT).show();
        memEdit.putString("ik_number",ik_number);
//
//        memEdit.putString("staff_id",mem_id);
        // prefsEdit.putString("staff_id",getIntent().getStringExtra("staff_id"));


        //prefsEdit.commit();
        //member.getString("staff_id","default");
//
        //final OnlineDBHelper db = new OnlineDBHelper(startmapping.this);
        recyclerView = findViewById(R.id.recyclerv_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //initiknumbers();
        //OnlineDBHelper db = new OnlineDBHelper(this);

//        Log.d("crop_type",crop_type);

        //mem = findViewById(R.id.editText);
        try {
            Intent intent = getIntent();
            Bundle b = intent.getExtras();

            staff_id = (String) b.get("staff_id");

//            prefsEdit.putString("MIN_WALKING_SPEED", intent.getStringExtra("max_walk_speed"));
//            prefsEdit.putString("MIN_BIKE_SPEED", intent.getStringExtra("max_bike_speed"));
            memEdit.commit();

            sessionManagement = new SessionManagement(getApplicationContext());
            sessionManagement.CreateLoginSession(staff_name, staff_id, staff_role);

//            @SuppressLint("StaticFieldLeak") Asynctask.DownloadApplication x = new Asynctask.DownloadApplication(startmapping.this) {
//                protected void onPostExecute(String s) {
//                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
//                    Log.d("RESULT__NOW", s);
//                }
//            };
//            x.execute();


        } catch (Exception e) {
            staff_id = "T-1999999";
            staff_role = "deji";
            staff_name = "Adeniran Adebayo";
            sessionManagement = new SessionManagement(getApplicationContext());
            sessionManagement.CreateLoginSession(staff_name, staff_id, staff_role);

//            @SuppressLint("StaticFieldLeak") Asynctask.DownloadApplication x = new Asynctask.DownloadApplication(startmapping.this) {
//                protected void onPostExecute(String s) {
//                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
//                    Log.d("RESULT__NOW", s);
//                }
//            };
//            x.execute();


        }
        String staffID = getIntent().getStringExtra("staff_id");
        String LGA= member.getString("lga","deji");
        //String ik_number= member.getString("ik_number","deji");

        Log.d("LGA",LGA);
        Log.d("LGA",mem_id);
        //String me="T-1000000000001536";
        //String you="IK011296";


        //loadRecyclerView(mem_id,LGA);
        loadRecyclerView(mem_id,ik_number);
    }

    public void loadRecyclerView(String staff_id,String lga){

        memberList2 = new ArrayList<>();
        OnlineDBHelper databaseOpenHelper = new OnlineDBHelper(getApplicationContext());
        memberList2 = databaseOpenHelper.load_applications(staff_id,lga);

        Log.d("--HELLO--1",memberList2+"");
        recyclerController(memberList2);
    }

//    }private void initiknumbers()
//    {final OnlineDBHelper db = new OnlineDBHelper(fieldselection.this);
//        final String staff_id="T-1999999";
//        //mem_id = getIntent().getStringExtra("staff_id");
//        //d=db.getstaffid();
//        //String staff_id= db.getstaffid();
//        //mNames=db.getiknumbers(staff_id);
//        //final ArrayList<String>
//               // String ik_number=getIntent().getStringExtra("ik_number");
//            String lga= member.getString("lga","deji");
//                //mNames=db.getmember_id(lga,mem_id);
//        String LGA="GWARZO";
//        String staffid="T-1000000000000465";
//
//        mNames=db.getmember_id(LGA,staffid);
//        firstname=new ArrayList();
//        lastname=new ArrayList();
//        phonenumber=new ArrayList();
//        number=new ArrayList();
//                for (int i =0;i<mNames.size();i++)
//                {
//                    String member=mNames.get(i);
//                    firstname.add(db.getfirstname(member));
////                    firstname=db.getfirstname(member);
//                    lastname.add(db.getlastname(member));
//                    phonenumber.add(db.getphonenumber(member));
//                    number.add(String.valueOf(0));
////                    initRecyclerView();
//
//
//
//                }
//                    //initRecyclerView();
////                firstname=db.getfirstname(mNames.toString());
////                lastname=db.getlastname(mNames.toString());
////                phonenumber=db.getphonenumber(mNames.toString());
////                prefsEdit.putString("first_name",firstname);
////                prefsEdit.putString("last_name",lastname);
////                prefsEdit.putString("phone_number",phonenumber);
////                String crop_type=db.getcroptype(mNames.toString());
////                prefsEdit.putString("crop_type",crop_type);
////                String mapping_date=db.getmapping_date(mNames.toString());
////                prefsEdit.putString("mapping_date",mapping_date);
////                String iknumbers=db.getiknumber(spinner_lga.getSelectedItem().toString(),mem_id).toString();
////                //prefsEdit.putString("ik_numbers",iknumbers);
////                String unique_id=db.getuniqueid(mNames.toString());
////                prefsEdit.putString("member_unique_id",unique_id);
////                String village=db.getvillage(mNames.toString());
////                prefsEdit.putString("village",village);
//
//
//               //String lga= member.getString("lga","deji");
//               // prefsEdit.commit();
//                //number=String.valueOf(db.membernumber(mem_id,member.getString("lga","lga")));
//                //mNames.add(staff_id);
//
//                //List<String> X = db.getmember_id(spinner_lga.getSelectedItem().toString());
//                //ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(startmapping.this, android.R.layout.simple_spinner_item, X);
//
//
//        Log.d("result", String.valueOf(mNames));
//        //mNames.add ("Deji");
//
//
//    }
//                mNames = db.getiknumbers(mem_id);
//                mNames.add(staff_id);
//                prefsEdit.putString("member_id",member_id);
        //mNames.add(list2);
        //RecyclerView recyclerView=findViewById(R.id.recyclerv_view);



//    private void initRecyclerView(){
//        Log.d(TAG,"initRecyclerView: init recyclerview.");
//        RecyclerView recyclerView=findViewById(R.id.recyclerv_view);
//        RecyclerViewAdapter2 adapter=new RecyclerViewAdapter2(mNames,firstname,lastname,number,phonenumber,this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//    }
    private void recyclerController( ArrayList<Map<String,String>> wordList){
        memberList1.clear();
        JSONArray jsonArray = new JSONArray(wordList);
        JSONObject jsonObject = null;
        for(int i = 0; i<jsonArray.length();i++){
            try {
                jsonObject = jsonArray.getJSONObject(i);
                memberList1.add(new modelclass(
                        jsonObject.getString("first_name"),
                        jsonObject.getString("last_name"),
                        jsonObject.getString("member_id"),
                        jsonObject.getString("phone_number")


                ));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        RecyclerViewAdapter2 tfmAdapter = new RecyclerViewAdapter2(memberList1,this);
        tfmAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(tfmAdapter);
    }

}
