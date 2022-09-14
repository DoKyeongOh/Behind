package org.mytoypjt.models.entity;

import java.util.Date;

public abstract class AbstractEntityLog {
    protected Date loggingDate;
    protected String actionType;
    protected int accountNo;

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

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }
}
