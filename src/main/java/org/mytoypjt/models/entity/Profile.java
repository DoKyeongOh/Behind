package org.mytoypjt.models.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Profile {
    int accountNo;
    String nicname;
    Date registerDate;
    String city;
    int age;
    String gender;
    int userLevel;

    public Profile(int accountNo) {
        this.accountNo = accountNo;
        this.nicname = "누군가";
        this.registerDate = new Date();
        this.city = "어딘가";
        this.age = 0;
        this.gender = "";
        this.userLevel = 1;
    }

    public Profile(int accountNo, String nicName, Date registerDate, String city, int age, String gender, int userLevel) {
        this.accountNo = accountNo;
        this.nicname = nicName;
        this.registerDate = registerDate;
        this.city = city;
        this.age = age;
        this.gender = parseGender(gender);
        this.userLevel = userLevel;
    }

    public static boolean isCorrectProfileNo(int profileNo){
        if (profileNo < 0) return false;
        return true;
    }

    public String parseGender(String gender){
        if (gender == null) return "X";
        if (gender.equals("남자")) return "M";
        if (gender.equals("여자")) return "W";
        return "M";
    }

    public static boolean isCorrectProfile(Profile profile) {
        if (profile == null) return false;
        if (profile.getGender().length() > 1) return false;
        if (profile.getNicname().equals("")) return false;
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

    public String getNicname() {
        return nicname;
    }

    public void setNicname(String nicname) {
        this.nicname = nicname;
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
}
