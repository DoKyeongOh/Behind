package org.mytoypjt.models.entity;

import java.util.Date;

public class CommentLog extends AbstractEntityLog{
    int commentLogNo;

    public CommentLog(int commentLogNo, Date loggingDate, String actionType, int accountNo, int entityNo) {
        this.commentLogNo = commentLogNo;
        this.loggingDate = loggingDate;
        this.actionType = actionType;
        this.accountNo = accountNo;
        this.entityNo = entityNo;
    }

    @Override
    public String getLogMsg() {
        return this.logMsg = "당신이 댓글을 "+ actionType +"하였습니다.";
    }

    public int getCommentLogNo() {
        return commentLogNo;
    }

    public void setCommentLogNo(int commentLogNo) {
        this.commentLogNo = commentLogNo;
    }
}
