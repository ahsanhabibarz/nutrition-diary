package com.example.nutritiondiary;

public class ParentsList extends PostID {

    String name,uid,phone;

    public ParentsList() {
    }

    public ParentsList(String name, String uid, String phone) {
        this.name = name;
        this.uid = uid;
        this.phone = phone;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
