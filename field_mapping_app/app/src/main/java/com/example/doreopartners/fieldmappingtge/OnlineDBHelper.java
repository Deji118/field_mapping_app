package com.example.doreopartners.fieldmappingtge;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnlineDBHelper extends SQLiteAssetHelper {
    //Asset Helper that houses details of CMP. code is readable enough
    private static final String DATABASE_NAME = "tfm.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "tfmtable";
    //    public static final String TABLE_NAME_LGA = "LGA";
//    public static final String STATE = "state";
//    public static final String CMP = "cmp";
//private static final String TABLE_NAME = "memberdetails";
    private static final String COLUMN_USER_MEMBERID = "member_id";

    private static final String COLUMN_USER_IKNUMBER = "ik_number";
    private static final String COLUMN_USER_FIRSTNAME = "first_name";
    private static final String COLUMN_USER_LASTNAME = "last_name";
    private static final String COLUMN_USER_PHONENUMBER = "phone_number";
    private static final String COLUMN_USER_CROPTYPE = "crop_type";
    private static final String COLUMN_USER_STATE = "state";
    private static final String COLUMN_USER_LGA = "lga";
    private static final String COLUMN_USER_UNIQUEID = "unique_id";
    private static final String COLUMN_USER_VILLAGE = "village";
    private static final String COLUMN_USER_MAPPINGDATE = "mapping_date";
    private static final String COLUMN_USER_MEMBERROLE = "member_role";
    private static final String COLUMN_USER_STAFFID = "staff_id";






    public String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public String STATE_TABLE = "select distinct staff_id from " + TABLE_NAME ;

    public OnlineDBHelper (Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
    }

    public List<String> getlga(String staffid_selected) {
        List<String> list = new ArrayList<String>();
        //list.add("Select lga...");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select distinct lga from tfmtable where staff_id=\""+staffid_selected+"\"",null);

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
    public List<String> getvillage(String staffid_selected,String role) {
        List<String> list = new ArrayList<String>();
        //list.add("Select lga...");
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT distinct village FROM tfmtable WHERE staff_id = '" + staffid_selected + "' AND member_role = '" + role + "'", null);


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
    public ArrayList<String> getmember_id(String lga_selected,String staffid_selected) {
        ArrayList<String> list = new ArrayList<String>();
        //list.add("Select lga...");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT distinct member_id FROM tfmtable WHERE lga = '" + lga_selected + "' AND staff_id = '" + staffid_selected + "'", null);


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
    public String getmemberrole(String lga_selected,String staffid_selected) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        String A = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select distinct member_role FROM tfmtable where lga=\"" + lga_selected + "' AND staff_id = '" + staffid_selected +"\"",null);

        if (cursor.moveToFirst()) {
            do {
                A = (cursor.getString(0));
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;
    }
    public ArrayList<String> getiknumber(String lga_selected,String staffid_selected) {
        ArrayList<String> list = new ArrayList<String>();
        //list.add("Select lga...");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT distinct ik_number FROM tfmtable WHERE lga = '" + lga_selected + "' AND staff_id = '" + staffid_selected + "'", null);


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
    //    public List<String> getLGA(String state_selected) {
//     List<String> list = new ArrayList<String>();
//       list.add("Select LGA...");
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select distinct lga from stateoforigins where state=\""+state_selected+"\"",null);
//
//        if (cursor.moveToFirst()){
//            do{
//                list.add(cursor.getString(0));
//            }
//            while(cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//
//        return list;
//    }
    public String getfirstname(String member_selected) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        String A = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select distinct first_name from tfmtable where member_id=\"" + member_selected + "\"", null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getString(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }
    public String getlastname(String member_selected) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        String A = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select distinct last_name from tfmtable where member_id=\"" + member_selected + "\"", null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getString(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }
    public String getphonenumber(String member_selected) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        String A = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select distinct phone_number from tfmtable where member_id=\"" + member_selected + "\"", null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getString(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }
    public String getcroptype(String member_selected) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        String A = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select distinct crop_type from tfmtable where member_id=\"" + member_selected + "\"", null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getString(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }
    public String getstate(String member_selected) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        String A = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select distinct state from tfmtable where member_id=\"" + member_selected + "\"", null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getString(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }
    public String getward(String member_selected) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        String A = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select distinct district from tfmtable where member_id=\"" + member_selected + "\"", null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getString(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }

    public String getmapping_date(String member_selected) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        String A = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select distinct mapping_date from tfmtable where member_id=\"" + member_selected + "\"", null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getString(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }

    public String getuniqueid(String member_selected) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        String A = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select distinct unique_id from tfmtable where member_id=\"" + member_selected + "\"", null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getString(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }
    public String getfirstname1(String staffid_selected,String lga_selected,String memberrole_selected) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        String A = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select distinct first_name from tfmtable where staff_id='" + staffid_selected + "' AND lga = '" + lga_selected + "' AND member_role = '" + memberrole_selected + "'", null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getString(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }    public String getlastname1(String staffid_selected,String lga_selected,String memberrole_selected) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        String A = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select distinct last_name from tfmtable where staff_id='" + staffid_selected + "' AND lga = '" + lga_selected + "' AND member_role = '" + memberrole_selected + "'", null);

        if (cursor.moveToFirst()){
            do{
                A=(cursor.getString(0));
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return A;

    }

    public Integer membernumber(String staffid_selected,String lga_selected) {

        //List<String> list = new ArrayList<String>();
        //list.add("Select state...");
        Integer A = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select  count (*)from tfmtable where staff_id=\"" + staffid_selected + "' AND lga = '" + lga_selected  + "\"", null);

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
    public ArrayList<Map<String, String>> load_applications(String staff_id,String ik_number) {
        Map<String, String> map = null;
        ArrayList<Map<String, String>> wordList;
        wordList = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT distinct first_name,last_name, member_id,phone_number FROM tfmtable  WHERE ik_number = \""+ik_number+"\" AND staff_id = \"" + staff_id +  "\"" , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            map = new HashMap<String, String>();
            map.put("first_name", cursor.getString(cursor.getColumnIndex("first_name")));
            map.put("last_name", cursor.getString(cursor.getColumnIndex("last_name")));
            map.put("member_id", cursor.getString(cursor.getColumnIndex("member_id")));
            map.put("phone_number", cursor.getString(cursor.getColumnIndex("phone_number")));

            wordList.add(map);
            Log.d("WordList",wordList.toString());
            cursor.moveToNext();

        }

        cursor.close();

        return wordList;
    }
    public ArrayList<Map<String, String>> load_ik(String staff_id,String village,String role) {
        Map<String, String> map = null;
        ArrayList<Map<String, String>> wordList;
        wordList = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT distinct first_name,last_name, ik_number FROM tfmtable  WHERE village = \""+village+"\" AND staff_id = \"" + staff_id +"\" AND member_role = \"" + role + "\"", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            map = new HashMap<String, String>();
            map.put("first_name", cursor.getString(cursor.getColumnIndex("first_name")));
            map.put("last_name", cursor.getString(cursor.getColumnIndex("last_name")));
            //map.put("member_id", cursor.getString(cursor.getColumnIndex("member_id")));
            map.put("ik_number", cursor.getString(cursor.getColumnIndex("ik_number")));

            wordList.add(map);
            Log.d("WordList",wordList.toString());
            cursor.moveToNext();

        }

        cursor.close();

        return wordList;
    }
    public  void getResults(ArrayList<Map<String, String>> wordList)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        JSONArray jsonArray = new JSONArray(wordList);
        JSONObject jsonObject = null;
        try {

            for(int i = 0; i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                db.execSQL("replace into "+TABLE_NAME+"("+COLUMN_USER_MEMBERID+","+COLUMN_USER_IKNUMBER+","+COLUMN_USER_FIRSTNAME+","+COLUMN_USER_LASTNAME+","+COLUMN_USER_PHONENUMBER+","+COLUMN_USER_CROPTYPE+","+COLUMN_USER_STATE+","+COLUMN_USER_LGA+","+COLUMN_USER_VILLAGE+","+COLUMN_USER_MAPPINGDATE+","+COLUMN_USER_MEMBERROLE+","+COLUMN_USER_UNIQUEID+","+COLUMN_USER_STAFFID+") values(\""+jsonObject.getString("member_id")+"\"," +
                        "\""+jsonObject.getString("ik_number")+"\",\""+jsonObject.getString("first_name")+"\",\""+jsonObject.getString("last_name")+"\",\""+jsonObject.getString("phone_number")+"\",\""+jsonObject.getString("crop_type")+"\",\""+jsonObject.getString("state")+"\",\""+jsonObject.getString("lga")+"\",\""+jsonObject.getString("village")+"\",\""+jsonObject.getString("mapping_date")+"\",\""+jsonObject.getString("member_role")+"\",\""+jsonObject.getString("unique_id")+"\",\""+jsonObject.getString("staff_id")+"\") ");

            }
        } catch (JSONException e) {
            Log.d("result3",e.toString());
            e.printStackTrace();
        }



    }
    public void updateSyncStatus(String id, String sync) {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "update user set status = '" + sync + "' where staff_id = '" + id + "'";
        db.execSQL(updateQuery);
    }
}


