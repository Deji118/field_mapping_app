package com.example.doreopartners.fieldmappingtge;
//RECYCLER VIEW FOR THE FIRST SET OF QUESTIONS (YES/NO QUESTIONS)
public class Set1 {
    String qNo;
    String q;
    String mem;
    String ans;

    public Set1(String qNo, String q, String mem, String ans) {
        this.qNo = qNo;
        this.q = q;
        this.mem = mem;
        this.ans = ans;
    }

    public String getqNo() {
        return qNo;
    }

    public String getQ() {
        return q;
    }

    public String getMem() {
        return mem;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
