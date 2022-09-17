package org.mytoypjt.models.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractEntityLog {
    protected String loggingDate;
    protected String actionType;
    protected int accountNo;
    protected int entityNo = -1;
    protected String logMsg = "";

    public AbstractEntityLog(String loggingDate, String actionType, int accountNo, int entityNo){
        this.loggingDate = loggingDate;
        this.actionType = actionType;
        this.accountNo = accountNo;
        this.entityNo = entityNo;
    }

    protected AbstractEntityLog() {
    }

    public abstract String getLogMsg();

    public void setLogMsg(String logMsg) {

    };

    public String getLoggingDate() {
        return loggingDate;
    }

    public void setLoggingDate(String loggingDate) {
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
