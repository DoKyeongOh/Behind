package org.mytoypjt.models.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Profile {
    int accountNo;
    String nickname;
    Date registerDate;
    String city;
    int age;
    String gender;
    int userLevel;

    public Profile(){}

    public Profile(int accountNo) {
        this.accountNo = accountNo;
        this.nickname = "누군가";
        this.registerDate = new Date();
        this.city = "어딘가";
        this.age = 0;
        this.gender = "";
        this.userLevel = 1;
    }

    public Profile(int accountNo, String nicName, Date registerDate, String city, int age, String gender, int userLevel) {
        this.accountNo = accountNo;
        this.nickname = nicName;
        this.registerDate = registerDate;
        this.city = city;
        this.age = age;
        this.gender = gender;
        this.userLevel = userLevel;
    }

    public static boolean isCorrectProfile(Profile profile) {
        if (profile == null) return false;
        if (profile.getGender().length() > 1) return false;
        if (profile.getNickname().equals("")) return false;
        if (profile.getAccountNo() < 1) return false;
        if (profile.getUserLevel() < 1) return false;
        return true;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
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

    public String parseGender() {
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

    public void injectProfile(Profile profile) {
        setNickname(profile.getNickname());
        setCity(profile.getCity());
        setAge(profile.getAge());
        setGender(profile.getGender());
    }
}
