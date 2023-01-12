package org.mytoypjt.models.entity;

import java.util.Date;

public class Reply {
    int replyNo;
    String content;
    int accountNo;
    int commentNo;
    boolean nameAnonymous;
    String nickname;

    Date repliedDate;

    public Reply(int replyNo){
        this.replyNo = replyNo;
        this.content = "";
        this.accountNo = -1;
        this.commentNo = -1;
        this.nameAnonymous = false;
        this.nickname = "";
        this.repliedDate = new Date();
    }

    public Reply(int replyNo, String content, int accountNo, int commentNo, boolean nameAnonymous, String nickname, Date repliedDate){
        this.replyNo = replyNo;
        this.content = content;
        this.accountNo = accountNo;
        this.commentNo = commentNo;
        this.nameAnonymous = nameAnonymous;
        this.nickname = nickname;
        this.repliedDate = repliedDate;
    }

    public Reply(String content, int accountNo, int commentNo, boolean nameAnonymous, String nickname){
        this.content = content;
        this.accountNo = accountNo;
        this.commentNo = commentNo;
        this.nameAnonymous = nameAnonymous;
        this.nickname = nickname;
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

    public String getnickname() {
        return nickname;
    }

    public void setnickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean getNameAnonymous() {
        return nameAnonymous;
    }

    public void setNameAnonymous(boolean nameAnonymous) {
        this.nameAnonymous = nameAnonymous;
    }

    public Date getRepliedDate() {
        return repliedDate;
    }

    public void setRepliedDate(Date repliedDate) {
        this.repliedDate = repliedDate;
    }
}
