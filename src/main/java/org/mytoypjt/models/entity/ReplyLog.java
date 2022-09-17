package org.mytoypjt.models.entity;

import java.util.Date;

public class ReplyLog extends AbstractEntityLog{

    int replyLogNo;

    public ReplyLog(int replyLogNo, String loggingDate, String actionType, int accountNo, int entityNo) {
        this.replyLogNo = replyLogNo;
        this.loggingDate = loggingDate;
        this.actionType = actionType;
        this.accountNo = accountNo;
        this.entityNo = entityNo;
    }

    @Override
    public String getLogMsg() {
        return this.logMsg = "당신이 대댓글을 "+ actionType +"하였습니다.";
    }

    public int getReplyLogNo() {
        return replyLogNo;
    }

    public void setReplyLogNo(int replyLogNo) {
        this.replyLogNo = replyLogNo;
    }
}
