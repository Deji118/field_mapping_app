package com.example.doreopartners.fieldmappingtge;
//RECYCLERVIEW FOR THE THIRD SET OF QUESTIONS
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Set3Adapter extends RecyclerView.Adapter<Set3Adapter.VH> {
    final ArrayList<Set3> trans;
    private final OnItemClickListener listener; //this is the listener that is set on view click, declared final because  "                  "                  "
    Context context;
    SharedPreferences prefs2;
    SharedPreferences.Editor prefsEdit;

//    Typeface roboto_bold;
//    Typeface roboto_italic;
//    Typeface roboto_regular;
    //Constructor initializes all of the above, notice it is the same as the normal constructor except now listener is added
    public Set3Adapter(Context context, ArrayList<Set3> trans, OnItemClickListener listener) {
        this.trans = trans;
        this.context = context;
        this.listener = listener;
        this.prefs2 = context.getSharedPreferences("prefs",Context.MODE_PRIVATE);
    }

    //Gets the view holder i.e the layout to hold each transaction view
    @Override
    public Set3Adapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.set3row, parent, false);
        return new VH(v);       //returns the view holder class below with the textviews assigned to ids
    }

    @Override
    public void onBindViewHolder(final Set3Adapter.VH holder, final int position) {
        final Set3 trans = this.trans.get(position);   //gets current transaction object from array

        holder.tvQNo.setText(trans.getqNo() + "");
        holder.tvQ.setText(trans.getQ());
        holder.tvMem.setText(trans.getMem());

        String[] crops = {"Maize", "Rice", "Soybean", "Groundnut", "Cowpea", "Sorghum","other"}; //the space in front is to make the spinner display a blank in default
        ArrayAdapter<String> adapter = new ArrayAdapter<> (context,android.R.layout.simple_list_item_1,crops);
        holder.spDry.setAdapter(adapter);
        holder.spRainy.setAdapter(adapter);
        holder.spDry.setSelection(trans.getDry());
        holder.spRainy.setSelection(trans.getRainy());


        holder.spDry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                final String selected = holder.spDry.getSelectedItem().toString();
                trans.setDry(p);
                //prefs2.edit().putString(("ans" + String.valueOf(position + 1) + "d").toString(), selected.toString()).commit();


                if (selected == "other")


                { holder.other.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {


                        if (s.length() > 0) {
                            //IS_FILLED0 = 1;
                            //trans.setAns(s.toString());
                            prefs2.edit().putString("ans" + String.valueOf(position + 1) + "d", s.toString()).commit();
                            prefs2.getString("ans1d", String.valueOf(s));
                            prefs2.getString("ans2d", String.valueOf(s));
                            prefs2.getString("ans3d", String.valueOf(s));
                            prefs2.getString("ans4d", String.valueOf(s));



                        } else {
                            //IS_FILLED0 = 0;
                        }

                    }
                });
                    holder.other.setVisibility(View.VISIBLE);
                    //holder.other2.setVisibility(View.GONE);
                    //holder.btother.setVisibility(View.VISIBLE);
//                    prefs.getString("ans1d", selected);
//                    prefs.getString("ans2d", selected);
//                    prefs.getString("ans3d", selected);
//                    prefs.getString("ans4d", selected);


                } else {
                    prefs2.edit().putString(("ans" + String.valueOf(position + 1) + "d").toString(), selected.toString()).commit();
                    //int crops[] = {prefs.getInt("ans1d", 0), prefs.getInt("ans1r", 0), prefs.getInt("ans2d", 0), prefs.getInt("ans2r", 0), prefs.getInt("ans3d", 0), prefs.getInt("ans3r", 0), prefs.getInt("ans4d", 0), prefs.getInt("ans4r", 0)};
//                    for (int i = 0; i < 8; i++) {
//                        if ((crops[i] == 2) || (crops[i] == 3) || (crops[i] == 4)) {
//                            //maize = true;
//                            break;
//                        }
//                    }
                    //String[] crops = {"Maize", "Rice", "Soybean", "Groundnut", "Cowpea", "Sorghum","other"};
//                    if (selected == "Maize") {//crops = {prefs.getString("ans1d", 0), prefs.getInt("ans1r", 0), prefs.getInt("ans2d", 0), prefs.getInt("ans2r", 0), prefs.getInt("ans3d", 0), prefs.getInt("ans3r", 0), prefs.get
//                        prefs2.getString("ans1d", selected);
////                        prefs.getString("ans2d", selected);
////                        prefs.getString("ans3d", "selected");
////                        prefs.getString("ans4d", "selected");
////                        prefsEdit.commit();
//                        holder.other.setVisibility(view.GONE);
//                        //holder.other2.setVisibility(view.GONE);
//                        //holder.btother.setVisibility(View.GONE);
//                    }
//                    if (selected == "Rice") {
////                        prefs.getString("ans1d", "selected");
////                        prefs.getString("ans2d", "selected");
////                        prefs.getString("ans3d", "selected");
////                        prefs.getString("ans4d", "selected");
////                        prefsEdit.commit();
//                        holder.other.setVisibility(view.GONE);
//                        //holder.other2.setVisibility(view.GONE);
//                        //holder.btother.setVisibility(View.GONE);
//                    }
//                    if (selected == "Soybean") {
////                        prefs.getString("ans1d", "selected");
////                        prefs.getString("ans2d", "selected");
////                        prefs.getString("ans3d", "selected");
////                        prefs.getString("ans4d", "selected");
////                        prefsEdit.commit();
//                        holder.other.setVisibility(view.GONE);
//                        //holder.other2.setVisibility(view.GONE);
//                        //holder.btother.setVisibility(View.GONE);
//                    }
//                    if (selected == "Groundnut") {
////                        prefs.getString("ans1d", "selected");
////                        prefs.getString("ans2d", "selected");
////                        prefs.getString("ans3d", "selected");
////                        prefs.getString("ans4d", "selected");
////                        prefsEdit.commit();
//                        holder.other.setVisibility(view.GONE);
//                        //holder.other2.setVisibility(view.GONE);
//                        //holder.btother.setVisibility(View.GONE);
//                    }
//                    if (selected == "Cowpea") {
////                        prefs.getString("ans1d", "selected");
////                        prefs.getString("ans2d", "selected");
////                        prefs.getString("ans3d", "selected");
////                        prefs.getString("ans4d", "selected");
////                        prefsEdit.commit();
//                        holder.other.setVisibility(view.GONE);
//                        //holder.other2.setVisibility(view.GONE);
//                        //holder.btother.setVisibility(View.GONE);
//                    }
//                    if (selected == "Sorghum") {
////                        prefs.getString("ans1d", "selected");
////                        prefs.getString("ans2d", "selected");
////                        prefs.getString("ans3d", "selected");
////                        prefs.getString("ans4d", "selected");
////                        prefsEdit.commit();
//                        holder.other.setVisibility(view.GONE);
//                        //holder.other2.setVisibility(view.GONE);
//                        //holder.btother.setVisibility(View.GONE);
//                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.spRainy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                trans.setRainy(p);

                String selected2 = holder.spRainy.getSelectedItem().toString();
                //prefs2.edit().putString("ans" + (position + 1) + "r", selected2).commit();
                //String selected22= prefs.getString("ans1d", "other");
                if (selected2 == "other") {   //prefs.getString("ans1d", selected2);
//                    prefsEdit.putString("ans2d", selected2);
//                    prefsEdit.putString("ans3d", selected2);
//                    prefsEdit.putString("ans4d", selected2);
//                    prefsEdit.commit();

                    holder.other2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {


                            if (s.length() > 0) {
                                //IS_FILLED0 = 1;
                                //trans.setAns(s.toString());
                                prefs2.edit().putString("ans" + (position + 1) + "r", s.toString()).commit();


                            } else {
                                //IS_FILLED0 = 0;
                            }

                        }
                    });
                    //holder.other.setVisibility(view.GONE);
                    holder.other2.setVisibility(View.VISIBLE);
                    //holder.btother2.setVisibility(View.VISIBLE);
                } else
                {
                    prefs2.edit().putString("ans" + (position + 1) + "r", selected2).commit();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        holder.tvQNo.setTypeface(roboto_italic);
//        holder.tvQ.setTypeface(roboto_bold);
//        holder.tvMem.setTypeface(roboto_italic);

    }

    @Override
    public int getItemCount() {
        return trans.size();
    }

    public interface OnItemClickListener {      //this is the interface that enables the onclick to work
        void onClick(Set3 trans);

    }

    public class VH extends RecyclerView.ViewHolder {
        TextView tvQNo;
        TextView tvQ;
        TextView tvMem;
        EditText other;
        EditText other2;
        Spinner spDry;
        Spinner spRainy;
        Button btother;
        Button btother2;



        public VH(View v) {
            super(v);
            tvQNo = v.findViewById(R.id.tvQNo);
            tvQ = v.findViewById(R.id.tvQ);
            tvMem = v.findViewById(R.id.tvMem);
            spDry = v.findViewById(R.id.spDry);
            spRainy = v.findViewById(R.id.spRainy);
            other=v.findViewById(R.id.other);
            other2=v.findViewById(R.id.otherrainy);


            //other.setVisibility(View.GONE);
//
//            roboto_bold = Typeface.createFromAsset(v.getContext().getAssets(), "font/roboto_bold.ttf");
//            roboto_italic = Typeface.createFromAsset(v.getContext().getAssets(), "font/roboto_italic.ttf");
//            roboto_regular = Typeface.createFromAsset(v.getContext().getAssets(), "font/roboto_bold.ttf");

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(trans.get(getLayoutPosition()));
                }
            });
//            btother.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent fp=new Intent(context,Set3Adapter.class);
//                    context.startActivity(fp);
//
//                }
//            });
        }
    }
}