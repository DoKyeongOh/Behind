package org.mytoypjt.models.entity;

import java.util.Date;

public class PostLog extends AbstractEntityLog {
    int postLogNo;
    int postNo;

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

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }
}
