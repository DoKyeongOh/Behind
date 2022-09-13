package org.mytoypjt.models.entity;

import lombok.Getter;
import lombok.Setter;

public class Account {
    int accountNo;
    String id;
    String password;
    String email;

    public Account(int profileNo, String id, String password, String email) {
        this.accountNo = profileNo;
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public Account(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static boolean isCorrectAccountNo(int accountNo) {
        if (accountNo < 0) return false;
        return true;
    }
}
