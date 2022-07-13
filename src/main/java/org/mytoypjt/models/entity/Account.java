package org.mytoypjt.models.entity;

import lombok.Getter;

@Getter
public class Account {
    int accountNo;
    String id;
    String password;
    String email;

    public Account(int userNo, String id, String password, String email) {
        this.accountNo = userNo;
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public Account(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }
}
