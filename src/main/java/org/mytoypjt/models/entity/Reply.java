package org.mytoypjt.models.entity;

import java.util.Date;

public class Reply {
    int replyNo;
    String content;
    int accountNo;
    int commentNo;
    boolean nameAnonymous;
    String nicname;

    Date repliedDate;

    public Reply(int replyNo){
        this.replyNo = replyNo;
        this.content = "";
        this.accountNo = -1;
        this.commentNo = -1;
        this.nameAnonymous = false;
        this.nicname = "";
        this.repliedDate = new Date();
    }

    public Reply(int replyNo, String content, int accountNo, int commentNo, boolean nameAnonymous, String nicname, Date repliedDate){
        this.replyNo = replyNo;
        this.content = content;
        this.accountNo = accountNo;
        this.commentNo = commentNo;
        this.nameAnonymous = nameAnonymous;
        this.nicname = nicname;
        this.repliedDate = repliedDate;
    }

    public Reply(String content, int accountNo, int commentNo, boolean nameAnonymous, String nicname){
        this.content = content;
        this.accountNo = accountNo;
        this.commentNo = commentNo;
        this.nameAnonymous = nameAnonymous;
        this.nicname = nicname;
        this.repliedDate = new Date();
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

    public boolean getnameAnonymous() {
        return nameAnonymous;
    }

    public void setUseAnonymousName(boolean useAnonymousName) {
        nameAnonymous = useAnonymousName;
    }

    public Date getRepliedDate() {
        return repliedDate;
    }

    public void setRepliedDate(Date repliedDate) {
        this.repliedDate = repliedDate;
    }
}
