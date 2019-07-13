package com.example.doreopartners.fieldmappingtge;//LANDING PAGE AFTER VERIFICATION FROM ACCESS CONTROL

//SQLIE DATABASE THAT FIELD DETAILS ARE SAVED TO AFTER TRACKING

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FieldMappingrevamp.db";


    private static final String TABLE_USER = "user";
    //
    private static final String COLUMN_USER_IKNUMBER = "ik_number";
    private static final String COLUMN_USER_FIRSTNAME = "first_name";
    private static final String COLUMN_USER_LASTNAME = "last_name";
    private static final String COLUMN_USER_MEMBERID = "member_id";
    private static final String COLUMN_USER_CROPTYPE = "crop_type";
    private static final String COLUMN_USER_STAFFID = "staff_id";
    private static final String COLUMN_USER_TFMUNIQUEID = "TFMuniqueid";
    private static final String COLUMN_USER_FIELDSIZE = "field_size";
    private static final String COLUMN_USER_LATLONGS = "latlongs";
    private static final String COLUMN_USER_TIMESTAMP = "timestamp";
    private static final String COLUMN_USER_DATE = "date";

    private static final String COLUMN_USER_MIDDLE = "middle";
    private static final String COLUMN_USER_MINLAT = "minlat";
    private static final String COLUMN_USER_MAXLAT = "maxlat";
    private static final String COLUMN_USER_MINLNG = "minlng";
    private static final String COLUMN_USER_MAXLNG = "maxlng";
    private static final String COLUMN_USER_QUESTION1 = "q1";
    private static final String COLUMN_USER_QUESTION2 = "q2";
    private static final String COLUMN_USER_QUESTION3 = "q3";
    private static final String COLUMN_USER_QUESTION4 = "q4";
    private static final String COLUMN_USER_QUESTION5 = "q5";
    private static final String COLUMN_USER_QUESTION6 = "q6";
    private static final String COLUMN_USER_QUESTION7 = "q7";
    private static final String COLUMN_USER_QUESTION8 = "q8";
    private static final String COLUMN_USER_QUESTION9 = "q9";
    private static final String COLUMN_USER_QUESTION10 = "q10";
    private static final String COLUMN_USER_QUESTION11 = "q11";
    private static final String COLUMN_USER_QUESTION12 = "q12";
    private static final String COLUMN_USER_QUESTION13 = "q13";
    private static final String COLUMN_USER_QUESTION14 = "q14";
    private static final String COLUMN_USER_DESCRIPTION = "description";
    private static final String COLUMN_USER_VILLAGE = "village";
    private static final String COLUMN_USER_QUESTION15AD = "q15ad";
    private static final String COLUMN_USER_QUESTION15AR = "q15ar";
    private static final String COLUMN_USER_QUESTION15BD = "q15bd";
    private static final String COLUMN_USER_QUESTION15BR = "q15br";
    private static final String COLUMN_USER_QUESTION15CD = "q15cd";
    private static final String COLUMN_USER_QUESTION15CR = "q15cr";
    private static final String COLUMN_USER_QUESTION15DD = "q15dd";
    private static final String COLUMN_USER_QUESTION15DR = "q15dr";
    private static final String COLUMN_USER_UNIQUEID = "unique_id";
    private static final String COlUMN_USER_VERSION = "version";
    private static final String COlUMN_USER_FIELDSTATUS = "field_status";





    //private static final String COlUMN_USER_TIME="date_uploaded";

    private static final String COLUMNN_USER_UPLOAD_STATUS="status";
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_USER_IKNUMBER + " TEXT, "
            + COLUMN_USER_FIRSTNAME + " TEXT,"+ COLUMN_USER_LASTNAME + " TEXT," +COLUMN_USER_MEMBERID + " TEXT," + COLUMN_USER_CROPTYPE
            + " TEXT," + COLUMN_USER_STAFFID + " TEXT," + COLUMN_USER_TFMUNIQUEID + " TEXT,"+ COLUMN_USER_FIELDSIZE + " TEXT," +
            COLUMN_USER_LATLONGS + " TEXT,"+COLUMN_USER_TIMESTAMP + " TEXT,"+COLUMN_USER_DATE+ " TEXT," +COLUMN_USER_MIDDLE + " TEXT,"+COLUMN_USER_MINLAT + " TEXT,"+COLUMN_USER_MAXLAT + " TEXT," +COLUMN_USER_MINLNG + " TEXT,"+COLUMN_USER_MAXLNG + " TEXT,"+ COLUMN_USER_QUESTION1 +
            " TEXT,"+ COLUMN_USER_QUESTION2 + " TEXT,"
            + COLUMN_USER_QUESTION3 + " TEXT," + COLUMN_USER_QUESTION4 + " TEXT," +
            COLUMN_USER_QUESTION5 + " TEXT," + COLUMN_USER_QUESTION6 + " TEXT," + COLUMN_USER_QUESTION7 + " TEXT,"+
            COLUMN_USER_QUESTION8 + " TEXT," + COLUMN_USER_QUESTION9 + " TEXT," + COLUMN_USER_QUESTION10 + " TEXT,"+
            COLUMN_USER_QUESTION11 + " TEXT," + COLUMN_USER_QUESTION12 + " TEXT," + COLUMN_USER_QUESTION13 + " TEXT," +
            COLUMN_USER_QUESTION14 + " TEXT," +COLUMN_USER_DESCRIPTION + " TEXT,"+COLUMN_USER_VILLAGE + " TEXT," + COLUMN_USER_QUESTION15AD+ " TEXT," + COLUMN_USER_QUESTION15AR+ " TEXT," +
            COLUMN_USER_QUESTION15BD + " TEXT," + COLUMN_USER_QUESTION15BR+ " TEXT," + COLUMN_USER_QUESTION15CD+ " TEXT," +
            COLUMN_USER_QUESTION15CR + " TEXT," + COLUMN_USER_QUESTION15DD+ " TEXT," + COLUMN_USER_QUESTION15DR+ " TEXT,"
            + COLUMN_USER_UNIQUEID+ " TEXT,"
            + COlUMN_USER_VERSION
            + " TEXT,"+COlUMN_USER_FIELDSTATUS
            + " TEXT ,"
            +COLUMNN_USER_UPLOAD_STATUS
            + " INTEGER DEFAULT 0" +")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }



    public String saverecords(JSONArray jsonArray){
        SQLiteDatabase database = getWritableDatabase();
        JSONObject jsonObject = null;
        for(int i = 0; i< jsonArray.length(); i++){
            try {
                //int Check  = 0;
                jsonObject = jsonArray.getJSONObject(i);
                //String check = "select count("+Note.Notes.STAFFID_ACTIVITYCODE_TIME+") from "+NOTES_TABLE_SUCCESSFUL_STATS+" where "+Note.Notes.STAFFID_ACTIVITYCODE_TIME+" = \""+jsonObject.getString("staffid_activitycode_time")+"\"";
                //Cursor cursor = database.rawQuery(check,null);
                //cursor.moveToFirst();


/*

                        String insert  = " insert into "+NOTES_TABLE_WORKING_STATS+"("+Note.Notes.STAFFID_ACTIVITYCODE_TIME+","+Note.Notes.ACTIVITY_ID+","+Note.Notes.ACTIVITY_NAME+","+Note.Notes.STAFF_ID+","+Note.Notes.PROCESS_COMPLETED+
                        ","+Note.Notes.TARGET+","+Note.Notes.TIME_COMPLETED+","+Note.Notes.TIME_CREATED+","+Note.Notes.TIME_UPDATED+") values(\""+jsonObject.getString("")+"\";
*/




                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_USER_IKNUMBER,jsonObject.getString("ik_number"));
                contentValues.put(COLUMN_USER_FIRSTNAME,jsonObject.getString("first_name"));
                contentValues.put(COLUMN_USER_LASTNAME,jsonObject.getString("last_name"));
                contentValues.put(COLUMN_USER_MEMBERID,jsonObject.getString("member_id"));
                contentValues.put(COLUMN_USER_CROPTYPE, jsonObject.getString("crop_type"));
                contentValues.put(COLUMN_USER_STAFFID,jsonObject.getString("staff_id"));
                contentValues.put(COLUMN_USER_TFMUNIQUEID, jsonObject.getString("TFMuniqueid"));
                contentValues.put(COLUMN_USER_FIELDSIZE,jsonObject.getString("field_size"));
                contentValues.put(COLUMN_USER_LATLONGS,jsonObject.getString("latlongs"));
                contentValues.put(COLUMN_USER_MIDDLE,jsonObject.getString("middle"));
                contentValues.put(COLUMN_USER_MINLAT,jsonObject.getString("minlat"));
                contentValues.put(COLUMN_USER_MAXLAT,jsonObject.getString("maxlat"));
                contentValues.put(COLUMN_USER_MINLNG,jsonObject.getString("minlng"));
                contentValues.put(COLUMN_USER_MAXLNG,jsonObject.getString("maxlng"));
                contentValues.put(COLUMN_USER_TIMESTAMP,jsonObject.getString("timestamp"));
                contentValues.put(COLUMN_USER_DATE,jsonObject.getString("date"));


                contentValues.put(COLUMN_USER_QUESTION1, jsonObject.getString("q1"));
                contentValues.put(COLUMN_USER_QUESTION2, jsonObject.getString("q2"));
                contentValues.put(COLUMN_USER_QUESTION3, jsonObject.getString("q3"));
                contentValues.put(COLUMN_USER_QUESTION4, jsonObject.getString("q4"));
                contentValues.put(COLUMN_USER_QUESTION5, jsonObject.getString("q5"));
                contentValues.put(COLUMN_USER_QUESTION6, jsonObject.getString("q6"));
                contentValues.put(COLUMN_USER_QUESTION7, jsonObject.getString("q7"));
                contentValues.put(COLUMN_USER_QUESTION8, jsonObject.getString("q8"));
                contentValues.put(COLUMN_USER_QUESTION9, jsonObject.getString("q9"));
                contentValues.put(COLUMN_USER_QUESTION10, jsonObject.getString("q10"));
                contentValues.put(COLUMN_USER_QUESTION11, jsonObject.getString("q11"));
                contentValues.put(COLUMN_USER_QUESTION12, jsonObject.getString("q12"));
                contentValues.put(COLUMN_USER_QUESTION13, jsonObject.getString("q13"));
                contentValues.put(COLUMN_USER_QUESTION14, jsonObject.getString("q14"));
                contentValues.put(COLUMN_USER_DESCRIPTION, jsonObject.getString("description"));
                contentValues.put(COLUMN_USER_VILLAGE, jsonObject.getString("village"));

                contentValues.put(COLUMN_USER_QUESTION15AD, jsonObject.getString("q15ad"));
                contentValues.put(COLUMN_USER_QUESTION15AR, jsonObject.getString("q15ar"));
                contentValues.put(COLUMN_USER_QUESTION15BD, jsonObject.getString("q15bd"));
                contentValues.put(COLUMN_USER_QUESTION15BR, jsonObject.getString("q15br"));
                contentValues.put(COLUMN_USER_QUESTION15CD, jsonObject.getString("q15cd"));
                contentValues.put(COLUMN_USER_QUESTION15CR, jsonObject.getString("q15cr"));
                contentValues.put(COLUMN_USER_QUESTION15DD, jsonObject.getString("q15dd"));
                contentValues.put(COLUMN_USER_QUESTION15DR, jsonObject.getString("q15dr"));
                contentValues.put(COLUMN_USER_UNIQUEID, jsonObject.getString("unique_id"));
                contentValues.put(COlUMN_USER_FIELDSTATUS, jsonObject.getString("field_status"));
                contentValues.put(COlUMN_USER_VERSION, jsonObject.getString("version"));




                database.insert(TABLE_USER,null,contentValues);
            } catch (JSONException e) {

                Log.d("deji4", e.toString());
                e.printStackTrace();
            }

        }





        return "";
    }
    public  JSONArray getResults()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String searchQuery = "SELECT  * FROM " + TABLE_USER+" where status = '0'";
        Cursor cursor = db.rawQuery(searchQuery, null );

        JSONArray resultSet     = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
                            Log.d("TAG_NAME", cursor.getString(i) );
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        }
                        else
                        {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                        Log.d("TAG_NAME", e.getMessage()  );
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();

        return resultSet;

    }
    public void updateSyncStatus(String id, String sync) {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "update user set status = '" + sync + "' where unique_id = '" + id + "'";
        db.execSQL(updateQuery);
    }
    public void updatefieldStatus(String id, String sync) {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "update user set field_status = '" + sync + "' where unique_id = '" + id + "'";
        db.execSQL(updateQuery);
    }
    public Integer memberfields(String memberid_selected) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        Integer A = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select  count (*)from user where member_id=\"" + memberid_selected   + "\"", null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getInt(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }

    public ArrayList<Map<String, String>> load_member(String village,String iknumber) {
        Map<String, String> map = null;
        ArrayList<Map<String, String>> wordList;
        wordList = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT distinct first_name,last_name, member_id FROM user  WHERE village = \""+village+"\" AND ik_number = \"" + iknumber + "\"", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            map = new HashMap<String, String>();
            map.put("first_name", cursor.getString(cursor.getColumnIndex("first_name")));
            map.put("last_name", cursor.getString(cursor.getColumnIndex("last_name")));
            map.put("member_id", cursor.getString(cursor.getColumnIndex("member_id")));


            wordList.add(map);
            Log.d("WordList",wordList.toString());
            cursor.moveToNext();

        }

        cursor.close();

        return wordList;
    }
    public Integer mapped_today(String date) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        Integer A = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select distinct count (*)from user where date=\"" + date   + "\"", null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getInt(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }
    public Integer mapped_total() {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        Integer A = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select  distinct count (*)from "+ TABLE_USER , null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getInt(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }
    public Integer synced(String status) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        Integer A = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select  count (*)from user where status=\"" + status   + "\"", null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getInt(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }
    public List<String> getvillage(String staffid_selected) {
        List<String> list = new ArrayList<String>();
        //list.add("Select lga...");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select distinct village from user where staff_id=\""+staffid_selected+"\"",null);

        if (cursor.moveToFirst()){
            do{
                list.add(cursor.getString(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }  public ArrayList<Map<String, String>> load_ik(String staff_id,String village) {
        Map<String, String> map = null;
        ArrayList<Map<String, String>> wordList;
        wordList = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT distinct ik_number FROM user  WHERE village = \""+village+"\" AND staff_id = \"" + staff_id + "\"", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            map = new HashMap<String, String>();

            map.put("ik_number", cursor.getString(cursor.getColumnIndex("ik_number")));

            wordList.add(map);
            Log.d("WordList",wordList.toString());
            cursor.moveToNext();

        }

        cursor.close();

        return wordList;
    }
    public ArrayList<Map<String, String>> load_activefields(String member_id,String fieldstatus) {
        Map<String, String> map = null;
        ArrayList<Map<String, String>> wordList;
        wordList = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT distinct description, unique_id,field_size,date FROM user  WHERE member_id = \""+member_id+ "\"AND field_status = \"" + fieldstatus + "\"", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            map = new HashMap<String, String>();

            map.put("unique_id", cursor.getString(cursor.getColumnIndex("unique_id")));
            map.put("description", cursor.getString(cursor.getColumnIndex("description")));

            map.put("field_size", cursor.getString(cursor.getColumnIndex("field_size")));
            map.put("date", cursor.getString(cursor.getColumnIndex("date")));
            wordList.add(map);
            Log.d("WordList",wordList.toString());
            cursor.moveToNext();

        }

        cursor.close();

        return wordList;
    }
    public ArrayList<Map<String, String>> load_inactivefields(String member_id,String fieldstatus) {
        Map<String, String> map = null;
        ArrayList<Map<String, String>> wordList;
        wordList = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT distinct description, unique_id,field_size,date FROM user  WHERE member_id = \""+member_id+ "\"AND field_status = \"" + fieldstatus + "\"", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            map = new HashMap<String, String>();

            map.put("unique_id", cursor.getString(cursor.getColumnIndex("unique_id")));
            map.put("description", cursor.getString(cursor.getColumnIndex("description")));
            map.put("field_size", cursor.getString(cursor.getColumnIndex("field_size")));
            map.put("date", cursor.getString(cursor.getColumnIndex("date")));




            wordList.add(map);
            Log.d("WordList",wordList.toString());
            cursor.moveToNext();

        }

        cursor.close();

        return wordList;
    }


    public  Cursor excelExport() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT member_id,ik_number,first_name,last_name,phone_number,age,sex,crop_type,state,lga,district,village," +
                "field_size,mapping_date,member_role,update_status,staff_id,unique_id,latitude,longitude,version_name,picPath,picName,tfm_date FROM memberT " , null);

        cursor.moveToFirst();

        db.close();
        return cursor;
    }
}
