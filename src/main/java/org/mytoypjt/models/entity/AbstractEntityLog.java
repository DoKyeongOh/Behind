package org.mytoypjt.models.entity;

import java.util.Date;

public abstract class AbstractEntityLog {
    protected Date loggingDate;
    protected String actionType;

    protected int accountNo;
    protected int entityNo = -1;
    protected String logMsg = "";

    public AbstractEntityLog(Date loggingDate, String actionType, int accountNo, int entityNo){
        this.loggingDate = loggingDate;
        this.actionType = actionType;
        this.accountNo = accountNo;
        this.entityNo = entityNo;
    }

    protected AbstractEntityLog() {
    }

    public String getLogMsg() {
        return logMsg;
    }

    public abstract void setLogMsg(String logMsg);

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

    public int getEntityNo() {
        return entityNo;
    }

    public void setEntityNo(int entityNo) {
        this.entityNo = entityNo;
    }
}
