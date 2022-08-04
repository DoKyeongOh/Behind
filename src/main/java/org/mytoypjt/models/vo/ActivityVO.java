package org.mytoypjt.models.vo;

import org.mytoypjt.models.entity.Profile;

import java.util.Date;

public class ActivityVO {

    public ActivityVO() {}

    public ActivityVO(Profile profile) {
        this.profile = profile;
    }

    public enum activityType {post, comment, reply}

    public enum workType {add, delete, change}

    Profile profile;
    Date activeDate;
    activityType activityType;
    workType workType;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public ActivityVO.activityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityVO.activityType activityType) {
        this.activityType = activityType;
    }

    public ActivityVO.workType getWorkType() {
        return workType;
    }

    public void setWorkType(ActivityVO.workType workType) {
        this.workType = workType;
    }
}
