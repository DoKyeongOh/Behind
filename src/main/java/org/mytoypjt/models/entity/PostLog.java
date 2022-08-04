package org.mytoypjt.models.entity;

import java.util.Date;

public class PostLog {
    int postLogNo;
    Date loggingDate;
    boolean isPost;
    int accountNo;

    public PostLog() {
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

    public boolean isPost() {
        return isPost;
    }

    public void setPost(boolean post) {
        isPost = post;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }
}
