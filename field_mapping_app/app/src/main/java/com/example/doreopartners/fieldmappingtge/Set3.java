package com.example.doreopartners.fieldmappingtge;

public class Set3 {
    String qNo;
    String q;
    String mem;
    int dry;
    int rainy;

    public Set3(String qNo, String q, String mem) {
        this.qNo = qNo;
        this.q = q;
        this.mem = mem;
        this.dry = dry;
        this.rainy = rainy;
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

    public int getDry() {
        return dry;
    }

    public int getRainy() {
        return rainy;
    }

    public void setDry(int dry) {
        this.dry = dry;
    }

    public void setRainy(int rainy) {
        this.rainy = rainy;
    }
}
