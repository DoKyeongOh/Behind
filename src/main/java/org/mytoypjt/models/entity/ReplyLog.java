package org.mytoypjt.models.entity;

import org.mytoypjt.models.etc.AbstractEntityLog;
import org.mytoypjt.models.etc.EntityLogTypeIdentifier;

public class ReplyLog extends AbstractEntityLog {

    int replyLogNo;

    public ReplyLog(int replyLogNo, String loggingDate, String actionType, int accountNo, int entityNo) {
        this.replyLogNo = replyLogNo;
        this.loggingDate = loggingDate;
        this.action = actionType;
        this.accountNo = accountNo;
        this.entityNo = entityNo;
        this.logTypeIdentifier = EntityLogTypeIdentifier.reply;
    }

    @Override
    public String getLogMsg() {
        return this.logMsg = "당신이 대댓글을 "+ action +"하였습니다.";
    }

    public int getReplyLogNo() {
        return replyLogNo;
    }

    public void setReplyLogNo(int replyLogNo) {
        this.replyLogNo = replyLogNo;
    }
}
