package com.example.doreopartners.fieldmappingtge;

//RECYCLER VIEW ADAPTER FOR DISPLAYING IKNUMBERS BASED ON STAFF LOGIN DETIALS
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String>mImageNames=new ArrayList<>();
    private ArrayList<String> firstname1;
    private ArrayList <String>lastname1;
    private ArrayList <String>number1;
    private ArrayList <String>phonenumber1;
    private List<modelclass> member_list;

    private Context mContext;
    SharedPreferences member;
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEdit;
    String TFMuniqueid;

    modelclass model;

    public RecyclerViewAdapter2(ArrayList<modelclass> memberList,Context context) {
        this.member_list=memberList;

//        firstname1=firstname2;
//        lastname1=lastname2;
//        number1=number2;
//        phonenumber1=phonenumber2;

        mContext=context;


    }


/*    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;


    }

    @Override
    public void onBindViewHolder( ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.imageName.setText(mImageNames.get(position));
        holder.firstname.setText(firstname1);
        holder.lastname.setText(lastname1);
        holder.number.setText(number1);

        holder.parentLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick: clicked on: "+ mImageNames.get(position));
                Toast.makeText(mContext,mImageNames.get(position), Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(mContext,Welcome.class);
                intent.putExtra("imagenames",mImageNames.get(position));
                OnlineDBHelper db=new OnlineDBHelper(mContext);

                //intent.putExtra("crop_type",db.getcroptype("imagenames"));
                //intent.putExtra("ik_number",db.getmemb("imagenames"));
                //mEditor.putString("ik_number",db.get("staff_id"));
                // intent.putExtra("first_name",db.getfirstname("imagenames"));
                //intent.putExtra("last_name",db.getlastname("imagenames"));
                //String ik_number=getIntent().getStringExtra("imagenames");

                //prefsEdit.putString("MIN_LOC_UPDATE_DISTANCE",intent.getStringExtra("min_dist"));
                intent.putExtra("MIN_WALKING_SPEED", intent.getStringExtra("max_walk_speed"));
                intent.putExtra("MIN_BIKE_SPEED", intent.getStringExtra("max_bike_speed"));
                Bundle b = intent.getExtras();


                mContext.startActivity(intent);
            }
        });
    }*/



    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem2,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;


    }
    @Override
    public void onBindViewHolder( ViewHolder holder, final int position) {
        //    public void onBindViewHolder( ViewHolder holder, final int position) {

        model = member_list.get(position);
        DatabaseHelper myDb;
        OnlineDBHelper db;
        db=new OnlineDBHelper(mContext);
        myDb = new DatabaseHelper(mContext);


        Log.d(TAG, "onBindViewHolder: called.");
        holder.imageName.setText(model.getMember_id());
        holder.firstname.setText(model.getFirst_name());
        //holder.lastname.setText(model.getLast_name());
        //holder.number.setText(number1.get(position));
        String first_name=model.getFirst_name();
        String last_name=model.getLast_name();
        holder.firstname.setText(first_name+" "+last_name);
        holder.phonenumber.setText(model.getPhone_number());
        int counter;
         counter=myDb.memberfields((model.getMember_id()));
         holder.number.setText(String.valueOf(counter));
        TFMuniqueid=db.getuniqueid(model.getMember_id());

        //            int counter;
//
//            counter = tglDB.check_member_id((member.getMember_id()).replace("R18","R19"));
//
//            if (counter !=  0){
////            holder.itemView.setBackgroundColor(R.drawable.customheader_green);
//                holder.itemView.setBackgroundResource(R.color.text_color_green);
//            }
//
//            holder.itemView.setLongClickable(true);
////        holder.itemView.setClickable(true);
//        }
//        @Override
//        public void onBindViewHolder( final MyViewHolder holder, final int position) {
//            member = memberList.get(position);
//
//            tglDB = TglDB.getInstance(mCtx);
//            tglDB.open();
//
//            Log.d("--HELLO--",member+"");
//            Log.d("Helllloooooo", member.getMember_id());
//            Log.d("Positioning", String.valueOf(position));
//
//            holder.memberRole.setText(member.getRole());
//            holder.memberID.setText(member.getMember_id());
//            holder.member_Name.setText(member.getFirst_name() + " " + member.getLast_name());
//
//            int counter;
//
//            counter = tglDB.check_member_id((member.getMember_id()).replace("R18","R19"));
//
//            if (counter !=  0){
////            holder.itemView.setBackgroundColor(R.drawable.customheader_green);
//                holder.itemView.setBackgroundResource(R.color.text_color_green);
//            }
//
//            holder.itemView.setLongClickable(true);
////        holder.itemView.setClickable(true);
//        }
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
        TextView phonenumbertext;
        TextView phonenumber;


        RelativeLayout parentLayout;
        public ViewHolder (View itemView)
        {
            super(itemView);
            imageName=itemView.findViewById(R.id.member_id);
            firstname=itemView.findViewById(R.id.firstname);
            //lastname=itemView.findViewById(R.id.lastname);
            membername=itemView.findViewById(R.id.membername);
            numbertext= itemView.findViewById(R.id.numbertext);
            number=itemView.findViewById(R.id.number);
            phonenumbertext=itemView.findViewById(R.id.phonenumbertext);
            phonenumber= itemView.findViewById(R.id.phonenumber);



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

                    final String name = firstname.getText().toString();
                    String []name2=name.split(" ");
                    String first_name=name2[0];
                    String last_name=name2[1];


                    //final TextView lastname = view.findViewById(R.id.lastname);

                    //final String last_name = lastname.getText().toString();
                    final TextView number = view.findViewById(R.id.number);

                    final String counter=number.getText().toString();


                    //Toast.makeText(mContext,mImageNames.get(position), Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(mContext,Welcome.class);
                    intent.putExtra("member_id",member_id);
                    intent.putExtra("first_name",first_name);
                    intent.putExtra("last_name",last_name);
                    intent.putExtra("counter",counter);
                    intent.putExtra("TFMuniqueid",TFMuniqueid);
                    Toast.makeText(mContext,first_name, Toast.LENGTH_SHORT).show();
                    OnlineDBHelper db = new OnlineDBHelper(mContext);


                    //intent.putExtra("ik_number",mImageNames.get(position));
                    //intent.putExtra("first_name",firstname);
                    //intent.putExtra("last_name",lastname);

                    //OnlineDBHelper db=new OnlineDBHelper(mContext);

                    //intent.putExtra("crop_type",db.getcroptype("imagenames"));
                    //intent.putExtra("ik_number",db.getmemb("imagenames"));
                    //mEditor.putString("ik_number",db.get("staff_id"));
                    // intent.putExtra("first_name",db.getfirstname("imagenames"));
                    //intent.putExtra("last_name",db.getlastname("imagenames"));
                    //String ik_number=getIntent().getStringExtra("imagenames");

                    //prefsEdit.putString("MIN_LOC_UPDATE_DISTANCE",intent.getStringExtra("min_dist"));
                    intent.putExtra("MIN_WALKING_SPEED", intent.getStringExtra("max_walk_speed"));
                    intent.putExtra("MIN_BIKE_SPEED", intent.getStringExtra("max_bike_speed"));
                    Bundle b = intent.getExtras();


                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }

}

