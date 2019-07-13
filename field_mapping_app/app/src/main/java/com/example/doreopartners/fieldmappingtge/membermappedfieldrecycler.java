package com.example.doreopartners.fieldmappingtge;

//RECYCLER VIEW ADAPTER FOR DISPLAYING IKNUMBERS BASED ON STAFF LOGIN DETIALS
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class membermappedfieldrecycler extends RecyclerView.Adapter<membermappedfieldrecycler.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String>mImageNames=new ArrayList<>();
    private ArrayList<String> firstname1;
    private ArrayList <String>lastname1;
    private ArrayList <String>number1;
    //private ArrayList <String>phonenumber1;
    private List<modelclass3> member_list;

    private Context mContext;
    SharedPreferences member;
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEdit;

    modelclass3 model3;

    public membermappedfieldrecycler(ArrayList<modelclass3> memberList, Context context) {
        this.member_list=memberList;
//        firstname1=firstname2;
//        lastname1=lastname2;
//        number1=number2;
//        phonenumber1=phonenumber2;

        mContext=context;


    }



    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.membermappedfieldrecycler,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;


    }
    @Override
    public void onBindViewHolder( ViewHolder holder, final int position) {
        //    public void onBindViewHolder( ViewHolder holder, final int position) {
        model3 = member_list.get(position);
        Log.d(TAG, "onBindViewHolder: called.");
        holder.imageName.setText(model3.getMember_id());
        //holder.firstname.setText(model3.getFirst_name());
        //holder.lastname.setText(model3.getLast_name());
        String first_name=model3.getFirst_name();
        String last_name=model3.getLast_name();
        holder.firstname.setText(first_name+" "+last_name);
//        holder.number.setText(number1.get(position));
        //holder.uniqueid.setText(model3.getUnique_id());

//        holder.parentLayout.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view) {
////                Log.d(TAG,"onClick: clicked on: "+ mImageNames.get(position));
//                //Toast.makeText(mContext,mImageNames.get(position), Toast.LENGTH_SHORT).show();
//                String member_id=model.getMember_id();
//                 String firstname=model.getFirst_name();
//                String lastname=model.getLast_name();
//
//                Intent intent= new Intent(mContext,Welcome.class);
//                intent.putExtra("member_id",member_id);
//                intent.putExtra("first_name",firstname);
//                intent.putExtra("last_name",lastname);
//                //Toast.makeText(this, member_id, LENGTH_SHORT).show();
//                Log.d("memberlee",member_id);
//
//                OnlineDBHelper db=new OnlineDBHelper(mContext);
//
//                //intent.putExtra("crop_type",db.getcroptype("imagenames"));
//                //intent.putExtra("ik_number",db.getmemb("imagenames"));
//                //mEditor.putString("ik_number",db.get("staff_id"));
//                // intent.putExtra("first_name",db.getfirstname("imagenames"));
//                //intent.putExtra("last_name",db.getlastname("imagenames"));
//                //String ik_number=getIntent().getStringExtra("imagenames");
//
//                //prefsEdit.putString("MIN_LOC_UPDATE_DISTANCE",intent.getStringExtra("min_dist"));
//                intent.putExtra("MIN_WALKING_SPEED", intent.getStringExtra("max_walk_speed"));
//                intent.putExtra("MIN_BIKE_SPEED", intent.getStringExtra("max_bike_speed"));
//                Bundle b = intent.getExtras();
//
//
//                mContext.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return member_list.size();
        // return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView imageName;
        TextView firstname;
        TextView lastname;
        TextView membername;
        TextView numbertext;
        TextView number;
        TextView uniqueidtext;
        TextView uniqueid;


        RelativeLayout parentLayout;
        public ViewHolder (View itemView)
        {
            super(itemView);
            imageName=itemView.findViewById(R.id.member_id);
            firstname=itemView.findViewById(R.id.firstname);
            //lastname=itemView.findViewById(R.id.lastname);
            membername=itemView.findViewById(R.id.membername);
            //numbertext= itemView.findViewById(R.id.numbertext);
            //number=itemView.findViewById(R.id.number);
           // uniqueidtext=itemView.findViewById(R.id.unique_idname);
         //   uniqueid= itemView.findViewById(R.id.unique_id);



            parentLayout=itemView.findViewById(R.id.parent_layout);
            parentLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
//                Log.d(TAG,"onClick: clicked on: "+ mImageNames.get(position));
//                    String iknumber=model.getik_number();
                    // String firstname=model.getFirst_name();
                    //String lastname=model.getLast_name();

                    final TextView memberid = view.findViewById(R.id.member_id);

                    final String member_id = memberid.getText().toString();
                    final TextView firstname = view.findViewById(R.id.firstname);

                    final String first_name = firstname.getText().toString();
                   // final TextView lastname = view.findViewById(R.id.lastname);

//                    final String last_name = lastname.getText().toString();
                    //prefsEdit.putString("member_id2",member_id);
                    //prefsEdit.commit();

                    //Toast.makeText(mContext,mImageNames.get(position), Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(mContext,activefield.class);
                    intent.putExtra("member_id2",member_id);

//                    intent.putExtra("first_name",first_name);
//                    intent.putExtra("last_name",last_name);
//                    Toast.makeText(mContext,member_id, Toast.LENGTH_SHORT).show();
//                    OnlineDBHelper db = new OnlineDBHelper(mContext);
//
//                    //intent.putExtra("ik_number",mImageNames.get(position));
//                    //intent.putExtra("first_name",firstname);
//                    //intent.putExtra("last_name",lastname);
//
//                    //OnlineDBHelper db=new OnlineDBHelper(mContext);
//
//                    //intent.putExtra("crop_type",db.getcroptype("imagenames"));
//                    //intent.putExtra("ik_number",db.getmemb("imagenames"));
//                    //mEditor.putString("ik_number",db.get("staff_id"));
//                    // intent.putExtra("first_name",db.getfirstname("imagenames"));
//                    //intent.putExtra("last_name",db.getlastname("imagenames"));
//                    //String ik_number=getIntent().getStringExtra("imagenames");
//
//                    //prefsEdit.putString("MIN_LOC_UPDATE_DISTANCE",intent.getStringExtra("min_dist"));
//                    intent.putExtra("MIN_WALKING_SPEED", intent.getStringExtra("max_walk_speed"));
////                    intent.putExtra("MIN_BIKE_SPEED", intent.getStringExtra("max_bike_speed"));
//                    Bundle b = intent.getExtras();
//
//
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }

}

