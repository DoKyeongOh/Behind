package org.mytoypjt.models.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Profile {
    int accountNo;
    String nicname;
    Date joinDate;
    String city;
    int age;
    String gender;
    int userLevel;

    public Profile(int accountNo) {
        this.accountNo = accountNo;
        this.nicname = "";
        this.joinDate = new Date();
        this.city = "";
        this.age = 0;
        this.gender = "";
        this.userLevel = 1;
    }

    public Profile(int profileNo, String nicName, Date joinDate, String city, int age, String gender, int userLevel) {
        this.accountNo = profileNo;
        this.nicname = nicName;
        this.joinDate = joinDate;
        this.city = city;
        this.age = age;
        this.gender = getGender(gender);
        this.userLevel = userLevel;
    }

    public static boolean isCorrectProfileNo(int profileNo){
        if (profileNo < 0) return false;
        return true;
    }

    public String getGender(String gender){
        if (gender == null) return "X";
        if (gender.equals("남자")) return "M";
        if (gender.equals("여자")) return "W";
        return "M";
    }
}
