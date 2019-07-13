package com.example.doreopartners.fieldmappingtge;//LANDING PAGE AFTER VERIFICATION FROM ACCESS CONTROL
//LANDING PAGE AFTER VERIFICATION FROM ACCESS CONTROL

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class activefield extends AppCompatActivity {
    private static final String TAG="viewmapped_fields";
    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<modelmappedfield> memberList1=new ArrayList<>();
    //private ArrayList<modelmappedfield> memberList1=new ArrayList<>();
    private ArrayList<modelmappedfield> number_list=new ArrayList<>();
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
    Spinner spinner_lga;
    TextView staffid;
    Button next;

    //SessionManagement sessionManagement;
    private RecyclerView recyclerView;
    SharedPreferences member;
    SharedPreferences prefs2;
    SharedPreferences.Editor memEdit;

    private JobScheduler jobScheduler;
    private JobInfo jobInfo;
    ComponentName componentName;
    private static final int JOB_ID =101;

    //TextView staffid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activefield);
        member = getSharedPreferences("member", MODE_PRIVATE);
        // prefs2 = getSharedPreferences("prefs", MODE_PRIVATE);
        memEdit = member.edit();
        OnlineDBHelper db = new OnlineDBHelper(this);
        //spinner_lga = findViewById(R.id.spinner_lga);
        staffid=findViewById(R.id.txtName);
        //staffid=findViewById(R.id.txtName);
        Log.d(TAG, "onCreate: started.");
        // Intent intent = getIntent();
        //String member_id = member.getString("member_id","001");

//        staffid.setText(mem_id);
        //memEdit.putString("staff_id",mem_id);
        //String member_id=getIntent().getStringExtra("member_id");
        // String lga = member.getString("lga", "deji");
        Toast.makeText(this, mem_id, Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.recyclerv_view);
        next=findViewById(R.id.next);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //initiknumbers();
        executeService();
        //mem = findViewById(R.id.editText);
        String member_id2=getIntent().getStringExtra("member_id2");
        memEdit.putString("member_id3",member_id2);
        memEdit.commit();

        staffid.setText(member_id2);
        String active="active";
        loadRecyclerView(member_id2,active);

        //loadRecyclerView(staffID,LGA,deji);
        //startActivity(new Intent(startmapping.this, startmapping.class));


    }


    public void loadRecyclerView(String staff_id,String active){
        memberList2 = new ArrayList<>();
        DatabaseHelper databaseOpenHelper = new DatabaseHelper(getApplicationContext());
        //memberList2 = databaseOpenHelper.load_ik(staff_id,lga);

        memberList2 = databaseOpenHelper.load_activefields(staff_id,active);


        Log.d("--HELLO--1",memberList2+"");
        recyclerController(memberList2);
//        boolean isReady =recyclerController(memberList2).ge>1;
//        if (recyclerController(memberList2)=={
//
//        }

    }

    private void recyclerController( ArrayList<Map<String,String>> wordList){
//        memberList1.clear();
        JSONArray jsonArray = new JSONArray(wordList);
        JSONObject jsonObject = null;
        for(int i = 0; i<jsonArray.length();i++) {
            try {
                jsonObject = jsonArray.getJSONObject(i);
                memberList1.add(new modelmappedfield(
                        jsonObject.getString("unique_id"),

                        jsonObject.getString("description"),
                        jsonObject.getString("field_size"),
                        jsonObject.getString("date")

                        //jsonObject.getString("phone_number")



                ));
                ;
            } catch (JSONException e) {
                e.printStackTrace();
            }

//        }   for(int i = 0; i<jsonArray.length();i++){
//            try {
//                jsonObject = jsonArray.getJSONObject(i);
//                number_list.add(new modelclass3(
//                        jsonObject.getString("number")
//
//
//
//                ));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

//        }
        }
        activefieldrecycler tfmAdapter = new activefieldrecycler(memberList1,this);
        tfmAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(tfmAdapter);
        tfmAdapter.notifyDataSetChanged();
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


    }   public void view_deleted(View v)
    {
        // starting background task to update product
        Intent fp=new Intent(getApplicationContext(),inactivefield.class);

        startActivity(fp);
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(activefield.this, mappedfieldik.class));
        finish();

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.example_menu,menu);
//        MenuItem searchItem=menu.findItem(R.id.action_search);
//        SearchView searchView=(SearchView) searchItem.getActionView();
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                                              @Override
//                                              public boolean onQueryTextSubmit(String query) {
//                                                  return false;
//                                              }
//
//                                              @Override
//                                              public boolean onQueryTextChange(String newText) {
//                                                  activefieldrecycler tfmAdapter = new activefieldrecycler(memberList1,getApplicationContext());
//                                                  tfmAdapter.notifyDataSetChanged();
//                                                  recyclerView.setAdapter(tfmAdapter);
//                                                  tfmAdapter.getFilter().filter(newText);
//                                                  tfmAdapter.notifyDataSetChanged();
//                                                  return false;
//                                              }
//                                          }
//        );
//        return true;
//    }
}
