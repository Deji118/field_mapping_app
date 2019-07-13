package com.example.doreopartners.fieldmappingtge;//LANDING PAGE AFTER VERIFICATION FROM ACCESS CONTROL

//RECYCLER VIEW ADAPTER FOR DISPLAYING IKNUMBERS BASED ON STAFF LOGIN DETIALS
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class activefieldrecycler extends RecyclerView.Adapter<activefieldrecycler.ViewHolder>  {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String>mImageNames=new ArrayList<>();
    private String firstname1;
    private String lastname1;
    private String number1;
    private Context mContext;
    SharedPreferences member;
    SharedPreferences prefs;
    SharedPreferences.Editor memEdit;
    //private List<modelclass3>number_list;
    modelmappedfield model;
    //modelclass3 model3;
    private List<modelmappedfield> member_list;
    private List<modelmappedfield> examplelistfull;
    // itemsCopy.addAll(items);

    public activefieldrecycler(ArrayList<modelmappedfield> memberList,Context context) {

        this.member_list=memberList;
        examplelistfull= new ArrayList<>(memberList);
        //this.number_list=number_list;
//        firstname1=firstname2;
//        lastname1=lastname2;
//        number1=number2;
//        phonenumber1=phonenumber2;

        mContext=context;
        //setHasStableIds(true);

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activefieldrecycler,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;



    }

    @Override
    public void onBindViewHolder( ViewHolder holder, final int position) {

        model = member_list.get(position);
//        model3=number_list.get(position);
        //for (int i = 0; i < member_list.size(); i++)
        //if (member_list.get(i))

        Log.d(TAG, "onBindViewHolder: called.");
        holder.imageName.setText(model.getField_id());
        holder.desciption.setText(model.getdescription());
        holder.field_size.setText(model.getField_size());
        holder.date.setText(model.getdate());
//        String first_name=model.getFirst_name();
//        String last_name=model.getLast_name();
//        holder.firstname.setText(first_name+" "+last_name);



        //holder.lastname.setText(model.getLast_name());
        //holder.number.setText(model3.getnumber());
        // holder.phonenumber.setText(model.getPhone_number());
//        setHasStableIds(true);

    }


    @Override
    public int getItemCount() {
        return member_list.size();
    }
//    @Override
//    public Filter getFilter()
//    {
//        return examplefilter;
//    }
//    private Filter examplefilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<modelmappedfield>filteredList =new ArrayList<>();
//            if (constraint==null || constraint.length()==0)
//            {
//                filteredList.addAll(examplelistfull);
//            }else
//            {
//                String filterPattern=constraint.toString().toLowerCase().trim();
//                for (modelmappedfield item :examplelistfull)
//                {
//                    if(item.getdescription().toLowerCase().contains(filterPattern))
//                    {
//                        filteredList.add(item);
//                    }
//                }
//            }
//            FilterResults results=new FilterResults();
//            results.values=filteredList;
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//        member_list.clear();
//        member_list.addAll((List)results.values);
//        notifyDataSetChanged();
//        }
//    };
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView imageName;
        TextView desciption;
        //TextView lastname;
        TextView field_id;
        TextView field_size;
        TextView date;



        RelativeLayout parentLayout;
        public ViewHolder (View itemView)
        {
            super(itemView);
            imageName=itemView.findViewById(R.id.field_id);
//            firstname=itemView.findViewById(R.id.firstname);
//            //lastname=itemView.findViewById(R.id.lastname);
//            leadername=itemView.findViewById(R.id.leadername);
            // numbertext= itemView.findViewById(R.id.numbertext);
            // number=itemView.findViewById(R.id.number);
            desciption=itemView.findViewById(R.id.description);
            field_size=itemView.findViewById(R.id.field_size);
            date=itemView.findViewById(R.id.date);

            parentLayout=itemView.findViewById(R.id.parent_layout);



            parentLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    final TextView ikTextView = view.findViewById(R.id.field_id);

                    final String field_id = ikTextView.getText().toString();

                    final String status="inactive";
                    final String sync_status="0";

                    new AlertDialog.Builder(mContext)
                            .setTitle("Delete Field")
                            .setMessage("Do you want to delete this field?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    //TODO 2. This is where I send the data to your app, check the response function for the keys
                                    Intent intent= new Intent(mContext,inactivefield.class);
                                    intent.putExtra("field_id",field_id);
                                    DatabaseHelper databaseHelper= new DatabaseHelper(mContext);
                                    databaseHelper.updatefieldStatus(field_id,status);
                                    databaseHelper.updateSyncStatus(field_id,sync_status);
                                    //String member_id=intent.getStringExtra("member_id2");
                                    //intent.putExtra("member_id3",member_id);

//                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                            intent.setComponent(new ComponentName("com.babbangona.tglapp","com.babbangona.tglapp.startmapping"));


                                    //databaseHelper.saverecords(jsonArray);


//                                            @SuppressLint("StaticFieldLeak") Asynctask2.UpploadTast x = new Asynctask2.UpploadTast(MappingForm.this) {
//                                                protected void onPostExecute(String s) {
//                                                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
//                                                    Log.d("RESULT__NOW", s);
//                                                }
//                                            };
//                                            x.execute();

                                    mContext.startActivity(intent);


                                    //startActivity(intent2);

                                    //Log.d("deji","ans1");

                                }
                            })

                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                            .show();
//                Log.d(TAG,"onClick: clicked on: "+ mImageNames.get(position));
//                    String iknumber=model.getik_number();
                    // String firstname=model.getFirst_name();
                    //String lastname=model.getLast_name();

                    //final TextView ikTextView = view.findViewById(R.id.iknumber);

                    //final String iknumber = ikTextView.getText().toString();


                    //Toast.makeText(mContext,mImageNames.get(position), Toast.LENGTH_SHORT).show();
                    //Intent intent= new Intent(mContext,membermappedfield.class);
                    //intent.putExtra("ik_number1",iknumber);
                    //Toast.makeText(mContext,iknumber, Toast.LENGTH_SHORT).show();
                    DatabaseHelper db = new DatabaseHelper(mContext);


                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }


}

