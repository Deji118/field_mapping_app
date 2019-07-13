package com.example.doreopartners.fieldmappingtge;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
//RECYCLER VIEW ADAPTER FOR THE FIRST SET OF QUESTIONS
public class Set1Adapter extends RecyclerView.Adapter<Set1Adapter.VH> {
    final ArrayList<Set1> trans; //holds the list of all transactions in database as transaction objects, declared final because it is used in an inner class
    private final OnItemClickListener listener; //this is the listener that is set on view click, declared final because  "                  "                  "
    Context context;    //this is used to store the context where the adapter will be used.
    SharedPreferences prefs2;
    private SharedPreferences.Editor mEditor;
    Typeface roboto_bold;
    Typeface roboto_italic;
    Typeface roboto_regular;

    //Constructor initializes all of the above, notice it is the same as the normal constructor except now listener is added
    public Set1Adapter(Context context, ArrayList<Set1> trans, OnItemClickListener listener) {
        this.trans = trans;
        this.context = context;
        this.listener = listener;
        this.prefs2 = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    //Gets the view holder i.e the layout to hold each transaction view
    @Override
    public Set1Adapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.set1row, parent, false);
        return new VH(v);       //returns the view holder class below with the textviews assigned to ids
    }

    @Override
    public void onBindViewHolder(Set1Adapter.VH holder, final int position) {
        final Set1 trans = this.trans.get(position);   //gets current transaction object from array

        holder.tvQNo.setText(trans.getqNo() + "");
        holder.tvQ.setText(trans.getQ());
        holder.tvMem.setText(trans.getMem());

        holder.rdbYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    trans.setAns("yes");
                    prefs2.edit().putString("ans" + (position + 1), "yes").commit();
                } else {
                    trans.setAns("no");
                    prefs2.edit().putString("ans" + (position + 1), "no").commit();
                }
            }
        });


        holder.tvQNo.setTypeface(roboto_italic);
        holder.tvQ.setTypeface(roboto_bold);
        holder.tvMem.setTypeface(roboto_italic);
        if (trans.getAns().equals("yes")) {
            holder.rdbYes.setChecked(true);
        } else {
            holder.rdbNo.setChecked(true);
        }

    }

    @Override
    public int getItemCount() {
        return trans.size();
    }

    public interface OnItemClickListener {      //this is the interface that enables the onclick to work
        void onClick(Set1 trans);
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView tvQNo;
        TextView tvQ;
        TextView tvMem;
        RadioGroup rdg;
        RadioButton rdbYes;
        RadioButton rdbNo;

        public VH(View v) {
            super(v);
            tvQNo = v.findViewById(R.id.tvQNo);
            tvQ = v.findViewById(R.id.tvQ);
            tvMem = v.findViewById(R.id.tvMem);
            rdbYes = v.findViewById(R.id.rdbYes);
            rdbNo = v.findViewById(R.id.rdbNo);

//            roboto_bold = Typeface.createFromAsset(v.getContext().getAssets(), "font/roboto_bold.ttf");
  //          roboto_italic = Typeface.createFromAsset(v.getContext().getAssets(), "font/roboto_italic.ttf");
    //        roboto_regular = Typeface.createFromAsset(v.getContext().getAssets(), "font/roboto_bold.ttf");


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(trans.get(getLayoutPosition()));
                }
            });

        }
    }
}