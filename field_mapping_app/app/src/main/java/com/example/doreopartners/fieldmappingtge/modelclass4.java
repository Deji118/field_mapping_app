package com.example.doreopartners.fieldmappingtge;

public class modelclass4 {



    private String first_name;
    private String last_name;
    private String unique_id;
    private String member_id;


    public modelclass4(String first_name, String last_name, String member_id, String phone_number ) {

        this.first_name = first_name;
        this.last_name = last_name;
        //this.unique_id = unique_id;
        this.member_id = member_id;

    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public String getMember_id() {
        return member_id;
    }
}