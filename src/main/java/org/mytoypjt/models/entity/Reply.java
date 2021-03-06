package org.mytoypjt.models.entity;

import java.util.Date;

public class Reply {
    int replyNo;
    String content;
    boolean isUseAnonymousName;
    int accountNo;
    int commentNo;
    String nicname;

    Date repliedDate;

    public Reply(int replyNo){
        this.replyNo = replyNo;
        this.content = "";
        this.isUseAnonymousName = false;
        this.accountNo = -1;
        this.commentNo = -1;
        this.nicname = "";
        this.repliedDate = new Date();
    }

    public Reply(int replyNo, String content, boolean isUseAnonymousName, int accountNo, int commentNo, String nicname, Date repliedDate){
        this.replyNo = replyNo;
        this.content = content;
        this.isUseAnonymousName = isUseAnonymousName;
        this.accountNo = accountNo;
        this.commentNo = commentNo;
        this.nicname = nicname;
        this.repliedDate = repliedDate;
    }

    public static boolean isCorrectReply(Reply reply){
        if (isNull(reply, reply.getContent()))
            return false;

        if (reply.getAccountNo() < 0)
            return false;

        if (reply.getReplyNo() < 0)
            return false;

        if (reply.getCommentNo() < 0)
            return false;

        return true;
    }

    public static boolean isNull(Object...param){
        for (Object str : param) {
            if (str == null)
                return true;
        }
        return false;
    }

    public int getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(int replyNo) {
        this.replyNo = replyNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getIsUseAnonymousName() {
        return isUseAnonymousName;
    }

    public void setUseAnonymousName(boolean useAnonymousName) {
        isUseAnonymousName = useAnonymousName;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public String getNicname() {
        return nicname;
    }

    public void setNicname(String nicname) {
        this.nicname = nicname;
    }
}
