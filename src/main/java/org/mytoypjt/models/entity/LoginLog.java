package org.mytoypjt.models.entity;

import org.mytoypjt.models.etc.AbstractEntityLog;
import org.mytoypjt.models.etc.EntityLogTypeIdentifier;

public class LoginLog extends AbstractEntityLog {

    int loginLogNo;

    public LoginLog(int loginLogNo, String loggingDate, String actionType, int accountNo){
        this.loginLogNo = loginLogNo;
        this.loggingDate = loggingDate;
        this.action = actionType;
        this.accountNo = accountNo;
        this.entityNo = -1;
        this.logTypeIdentifier = EntityLogTypeIdentifier.login;
    }

    @Override
    public String getLogMsg() {
        return null;
    }
}
