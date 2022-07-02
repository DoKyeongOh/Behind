package org.mytoypjt.entity;

import java.util.Date;

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

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getNicname() {
        return nicname;
    }

    public void setNicname(String nicname) {
        this.nicname = nicname;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }
}
