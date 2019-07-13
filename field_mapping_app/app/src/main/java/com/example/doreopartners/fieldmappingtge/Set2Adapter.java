package com.example.doreopartners.fieldmappingtge;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
//RECYCLERVIEW ADAPTER FOR THE SECOND SET OF QUESTIONS
public class Set2Adapter extends RecyclerView.Adapter<Set2Adapter.VH> {
    final ArrayList<Set1> trans; //holds the list of all transactions in database as transaction objects, declared final because it is used in an inner class
    private final OnItemClickListener listener; //this is the listener that is set on view click, declared final because  "                  "                  "
    Context context;
    SharedPreferences prefs2;

    static int IS_FILLED0 = 0;
    static int IS_FILLED1 = 0;
    static int IS_FILLED2 = 0;
    Typeface roboto_bold;
    Typeface roboto_italic;
    Typeface roboto_regular;

    //Constructor initializes all of the above, notice it is the same as the normal constructor except now listener is added
    public Set2Adapter(Context context, ArrayList<Set1> trans, OnItemClickListener listener) {
        this.trans = trans;
        this.context = context;
        this.listener = listener;
        this.prefs2 = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    //Gets the view holder i.e the layout to hold each transaction view
    @Override
    public Set2Adapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.set2row, parent, false);
        return new VH(v);       //returns the view holder class below with the textviews assigned to ids
    }

    @Override
    public void onBindViewHolder(Set2Adapter.VH holder, final int position) {
        final Set1 trans = this.trans.get(position);   //gets current transaction object from array

        holder.tvQNo.setText(trans.getqNo() + "");
        holder.tvQ.setText(trans.getQ());
        holder.tvMem.setText(trans.getMem());
        holder.etAmt.setText(trans.getAns());
        //checked if the variables are filled or not

        holder.etAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                switch (position) {
                    case 0:
                        if (s.length() > 0) {
                            IS_FILLED0 = 1;
                            trans.setAns(s.toString());
                            prefs2.edit().putString("ans" + (position + 1) + "a", s.toString()).commit();
                        } else {
                            IS_FILLED0 = 0;
                        }
                        break;
                    case 1:
                        if (s.length() > 0) {
                            IS_FILLED1 = 1;
                            trans.setAns(s.toString());
                            prefs2.edit().putString("ans" + (position + 1) + "a", s.toString()).commit();
                        } else {
                            IS_FILLED1 = 0;
                        }
                        break;
                    case 2:
                        if (s.length() > 0) {
                            IS_FILLED2 = 1;
                            trans.setAns(s.toString());
                            prefs2.edit().putString("ans" + (position + 1) + "a", s.toString()).commit();
                        } else {
                            IS_FILLED2 = 0;
                        }
                        break;

                }
            }
        });

        holder.tvQNo.setTypeface(roboto_italic);
        holder.tvQ.setTypeface(roboto_bold);
        holder.tvMem.setTypeface(roboto_italic);

    }

    public boolean isFilled() {
        if ((IS_FILLED0 == 1)&&(IS_FILLED1 == 1)&&(IS_FILLED2 == 1)) {
            return true;
        }
        return false;
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
        EditText etAmt;

        public VH(View v) {
            super(v);
            tvQNo = v.findViewById(R.id.tvQNo);
            tvQ = v.findViewById(R.id.tvQ);
            tvMem = v.findViewById(R.id.tvMem);
            etAmt = v.findViewById(R.id.etAmt);

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