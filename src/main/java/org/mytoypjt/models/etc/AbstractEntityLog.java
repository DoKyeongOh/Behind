package org.mytoypjt.models.etc;

public abstract class AbstractEntityLog {
    protected String loggingDate;
    protected String action;
    protected int accountNo;
    protected int entityNo = -1;
    protected String logMsg = "";

    public AbstractEntityLog(String loggingDate, String action, int accountNo, int entityNo){
        this.loggingDate = loggingDate;
        this.action = action;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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
