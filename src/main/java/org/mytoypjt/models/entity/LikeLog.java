package org.mytoypjt.models.entity;

import org.mytoypjt.models.etc.AbstractEntityLog;

public class LikeLog extends AbstractEntityLog {

    int likeLogNo;

    public LikeLog(int likeLogNo, String loggingDate, String actionType, int accountNo, int entityNo){
        this.likeLogNo = likeLogNo;
        this.loggingDate = loggingDate;
        this.action = actionType;
        this.accountNo = accountNo;
        this.entityNo = entityNo;
    }

    public int getLikeLogNo() {
        return likeLogNo;
    }

    public void setLikeLogNo(int likeLogNo) {
        this.likeLogNo = likeLogNo;
    }

    @Override
    public String getLogMsg() {
        String action = this.action.equals("좋아요") ? "좋아합니다." : "좋아하지 않습니다.";
        return this.logMsg = "당신이 게시글을 " + action;
    }
}
