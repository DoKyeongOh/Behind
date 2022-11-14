package org.mytoypjt.models.entity;

import org.mytoypjt.models.etc.AbstractEntityLog;
import org.mytoypjt.models.etc.EntityLogTypeIdentifier;

public class LoginLog {

    int loginLogNo;
    String loggingDate;
    String action;
    int accountNo;

    public LoginLog(int loginLogNo, String loggingDate, String actionType, int accountNo){
        this.loginLogNo = loginLogNo;
        this.loggingDate = loggingDate;
        this.action = actionType;
        this.accountNo = accountNo;
    }

    public int getLoginLogNo() {
        return loginLogNo;
    }

    public void setLoginLogNo(int loginLogNo) {
        this.loginLogNo = loginLogNo;
    }

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
}
