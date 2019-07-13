package com.example.doreopartners.fieldmappingtge;

public class modelclassikmapped {



    private String first_name;
    private String last_name;
    private String ik_number;
    private String number;



    public modelclassikmapped( String ik_number ) {

        this.ik_number = ik_number;
        //this.member_id = member_id;

    }



    public String getik_number() {
        return ik_number;
    }

//    public String getMember_id() {
//        return member_id;
//    }
}