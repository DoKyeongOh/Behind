package org.mytoypjt.models.vo;

import org.mytoypjt.models.entity.Profile;

import java.util.Calendar;
import java.util.Date;

public class UserVO {

    Profile profile;
    Date expireDate;

    public UserVO(Profile profile) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        this.profile = profile;
        this.expireDate = cal.getTime();
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "profileNo=" + profile.getAccountNo() +
                ", expireDate=" + expireDate +
                '}';
    }
}
