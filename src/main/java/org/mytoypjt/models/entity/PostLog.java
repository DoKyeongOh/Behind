package org.mytoypjt.models.entity;

import java.util.Date;

public class PostLog extends AbstractEntityLog {
    int postLogNo;

    public PostLog(Date loggingDate, String actionType, int accountNo, int targetNo) {
        super(loggingDate, actionType, accountNo, targetNo);
    }

    public PostLog(int postLogNo, Date loggingDate, String actionType, int accountNo, int entityNo){
        this.postLogNo = postLogNo;
        this.loggingDate = loggingDate;
        this.actionType = actionType;
        this.accountNo = accountNo;
        this.entityNo = entityNo;
    }

    @Override
    public void setLogMsg(String logMsg) {
        this.logMsg = "당신이 게시글을 "+ actionType +"하였습니다.";
    }

    public int getPostLogNo() {
        return postLogNo;
    }

    public void setPostLogNo(int postLogNo) {
        this.postLogNo = postLogNo;
    }

}
