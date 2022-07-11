package org.mytoypjt.models.entity;

import lombok.Getter;

import java.util.Date;

@Getter
public class User {
    int userNo;
    String nicname;
    Date joinDate;
    String city;
    int age;
    String gender;
    int userLevel;

    public User(int userNo, String nicName, Date joinDate, String city, int age, String gender, int userLevel) {
        this.userNo = userNo;
        this.nicname = nicName;
        this.joinDate = joinDate;
        this.city = city;
        this.age = age;
        this.gender = gender;
        this.userLevel = userLevel;
    }

    public static boolean isCorrectUserNo(int userNo){
        if (userNo < 0) return false;
        return true;
    }
}
