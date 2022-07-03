package org.mytoypjt.models.entity;

public class Account {
    int userNo;
    String id;
    String password;

    public Account(int userNo, String id, String password) {
        this.userNo = userNo;
        this.id = id;
        this.password = password;
    }

    public Account(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }
}
