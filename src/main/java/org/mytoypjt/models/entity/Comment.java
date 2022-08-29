package org.mytoypjt.models.entity;

import java.util.Date;

public class Comment {

    public Comment() {
    }
    int commentNo;
    String content;
    int replyCount;
    int accountNo;
    int postNo;
    String nicname;
    boolean isUseAnonymousName;

    Date commentedDate;


    public Comment(int commentNo) {
        this.commentNo = commentNo;
        this.content = "";
        this.replyCount = 0;
        this.accountNo = -1;
        this.postNo = -1;
        this.isUseAnonymousName = false;
        this.nicname = "";
        this.commentedDate = new Date();
    }

    public Comment(int commentNo, String content, int replyCount, int accountNo, int postNo, boolean isUseAnonymousName,String nicname, Date commentedDate) {
        this.commentNo = commentNo;
        this.content = content;
        this.replyCount = replyCount;
        this.accountNo = accountNo;
        this.postNo = postNo;
        this.isUseAnonymousName = isUseAnonymousName;
        this.nicname = nicname;
        this.commentedDate = commentedDate;
    }

    public Comment(String content, int replyCount, int accountNo, int postNo, String nicname, boolean isUseAnonymousName, Date commentedDate) {
        this.content = content;
        this.replyCount = replyCount;
        this.accountNo = accountNo;
        this.postNo = postNo;
        this.nicname = nicname;
        this.isUseAnonymousName = isUseAnonymousName;
        this.commentedDate = commentedDate;
    }

    public static boolean isCorrectComment(Comment comment){
        if (isNull(comment, comment.getContent()))
            return true;

        if (comment.getAccountNo() < 0)
            return false;

        if (comment.getPostNo() < 0)
            return false;

        if (comment.getCommentNo() < 0)
            return false;

        if (comment.getReplyCount() < 0)
            return false;

        return false;
    }

    public static boolean isNull(Object...param){
        for (Object str : param) {
            if (str == null)
                return true;
        }
        return false;
    }


    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public String getNicname() {
        return nicname;
    }

    public void setNicname(String nicname) {
        this.nicname = nicname;
    }

    public Date getCommentedDate() {
        return commentedDate;
    }

    public void setCommentedDate(Date commentedDate) {
        this.commentedDate = commentedDate;
    }
}
