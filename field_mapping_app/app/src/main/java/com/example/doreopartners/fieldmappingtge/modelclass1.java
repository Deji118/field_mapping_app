package com.example.doreopartners.fieldmappingtge;

public class modelclass1 {



    private String first_name;
    private String last_name;
    private String ik_number;
    private String number;



    public modelclass1(String first_name, String last_name, String ik_number ) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.ik_number = ik_number;
        //this.member_id = member_id;

    }



    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getik_number() {
        return ik_number;
    }

//    public String getMember_id() {
//        return member_id;
//    }
}