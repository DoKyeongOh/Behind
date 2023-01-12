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

    public void checkConvention() {
        if (gender.length() > 1) {
            throw new CustomException(ErrorCode.PROFILE_INPUT_GENDER_INCORRECT);
        }
        if (nickname.isEmpty() || nickname.length() > 15) {
            throw new CustomException(ErrorCode.PROFILE_INPUT_NICKNAME_INCORRECT);
        }
        if (nickname.contains(" ")) {
            throw new CustomException(ErrorCode.PROFILE_INPUT_NICKNAME_HAS_BLANK);
        }
        if (accountNo == null) {
            throw new CustomException(ErrorCode.PROFILE_INPUT_ITEM_HAS_NULL);
        }
    }

    public void convert(Profile profile) {
        this.nickname = profile.getNickname();
        this.city = profile.getCity();
        this.age = profile.getAge();
        this.gender = profile.getGender();
        this.userLevel = profile.userLevel;
    }

}
