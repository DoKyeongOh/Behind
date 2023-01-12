package org.mytoypjt.models.entity;

import lombok.Getter;
import org.mytoypjt.exception.CustomException;
import org.mytoypjt.exception.ErrorCode;

import java.util.Date;

@Getter
public class Profile {
    Integer accountNo;
    String nickname;
    Date registerDate;
    String city;
    Integer age;
    String gender;
    Integer userLevel;

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

    public Profile(int accountNo, String nickname, Date registerDate, String city, int age, String gender, int userLevel) {
        this.accountNo = accountNo;
        this.nickname = nickname;
        this.registerDate = registerDate;
        this.city = city;
        this.age = age;
        this.gender = gender;
        this.userLevel = userLevel;
    }

    }

    }

}
