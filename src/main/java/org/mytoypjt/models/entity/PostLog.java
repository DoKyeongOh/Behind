package org.mytoypjt.models.entity;

import java.util.Date;

public class PostLog {
    int postLogNo;
    Date loggingDate;
    String actionType;

    int postNo;
    int accountNo;

    public PostLog() {
    }

    public PostLog(int postLogNo, Date loggingDate, String actionType, int postNo, int accountNo) {
        this.postLogNo = postLogNo;
        this.loggingDate = loggingDate;
        this.actionType = actionType;
        this.postNo = postNo;
        this.accountNo = accountNo;
    }

    public int getPostLogNo() {
        return postLogNo;
    }

    public void setPostLogNo(int postLogNo) {
        this.postLogNo = postLogNo;
    }

    public Date getLoggingDate() {
        return loggingDate;
    }

    public void setLoggingDate(Date loggingDate) {
        this.loggingDate = loggingDate;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }
}
