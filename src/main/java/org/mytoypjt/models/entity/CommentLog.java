package org.mytoypjt.models.entity;

import org.mytoypjt.models.etc.AbstractEntityLog;
import org.mytoypjt.models.etc.EntityLogTypeIdentifier;

public class CommentLog extends AbstractEntityLog {
    int commentLogNo;

    public CommentLog(int commentLogNo, String loggingDate, String actionType, int accountNo, int entityNo) {
        this.commentLogNo = commentLogNo;
        this.loggingDate = loggingDate;
        this.action = actionType;
        this.accountNo = accountNo;
        this.entityNo = entityNo;
        this.logTypeIdentifier = EntityLogTypeIdentifier.comment;
    }

    @Override
    public String getLogMsg() {
        return this.logMsg = "당신이 댓글을 "+ action +"하였습니다.";
    }

    public int getCommentLogNo() {
        return commentLogNo;
    }

    public void setCommentLogNo(int commentLogNo) {
        this.commentLogNo = commentLogNo;
    }
}
