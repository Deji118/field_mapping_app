package com.example.doreopartners.fieldmappingtge;
//I STILL DONT KNOW WHY THIS IS HERE, STILL TRYING TO FIGURE OUT
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class recordhelper extends SQLiteAssetHelper{


        private static final String DATABASE_NAME = "stateoforigins.db";
        private static final int DATABASE_VERSION = 1;

        public static final String TABLE_NAME = "stateoforigins";


        public String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public String STATE_TABLE = "select distinct state from " + TABLE_NAME ;

        public recordhelper (Context context) {
            super (context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
        }

        public List<String> getstate() {
            List<String> list = new ArrayList<String>();
            list.add("Select state...");
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(STATE_TABLE,null);

            if (cursor.moveToFirst()){
                do{
                    list.add(cursor.getString(0));
                }
                while(cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return list;
        }


        public List<String> getLGA(String state_selected) {
            List<String> list = new ArrayList<String>();
            list.add("");
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("select distinct LGA from stateoforigins where state=\""+state_selected+"\"",null);


            if (cursor.moveToFirst()){
                do{
                    list.add(cursor.getString(0));
                }
                while(cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return list;
        }
        public List<String> getCMP(String lga_selected) {

            List<String> list = new ArrayList<String>();
            //list.add("Select CMP...");

            SQLiteDatabase db = this.getReadableDatabase();
            //Cursor cursor = db.rawQuery("select distinct cmp from stateoforigins where state=\""+state_selected+"\"",null);
            //Cursor cursor = db.rawQuery("select distinct CMP from stateoforigins where lga=\""+lga_selected+"\"",null);
            Cursor cursor = db.rawQuery("select CMP FROM stateoforigins where lga=\""+lga_selected+"\"",null);
            if (cursor.moveToFirst()){
                do{
                    list.add(cursor.getString(0));
                }
                while(cursor.moveToNext());
            }
            cursor.close();
            db.close();
//
//
//
//        if (cursor2.moveToFirst()){
//            do{
//                list.add(cursor2.getString(0));
//            }
//            while(cursor2.moveToNext());
//        }
//        cursor2.close();
//        db.close();

            return list;


        }




}
