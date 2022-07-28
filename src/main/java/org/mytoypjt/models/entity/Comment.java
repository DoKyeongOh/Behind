package org.mytoypjt.models.entity;

import java.util.Date;

public class Comment {

    public Comment() {
    }
    int commentNo;
    String content;
    int replyCount;
    boolean isUseAnonymousName;
    int accountNo;
    int postNo;
    String nicname;

    Date commentedDate;


    public Comment(int commentNo) {
        this.commentNo = commentNo;
        this.content = "";
        this.replyCount = 0;
        this.isUseAnonymousName = false;
        this.accountNo = -1;
        this.postNo = -1;
        this.nicname = "";
        this.commentedDate = new Date();
    }

    public Comment(int commentNo, String content, int replyCount, boolean isUseAnonymousName, int accountNo, int postNo, String nicname, Date commentedDate) {
        this.commentNo = commentNo;
        this.content = content;
        this.replyCount = replyCount;
        this.isUseAnonymousName = isUseAnonymousName;
        this.accountNo = accountNo;
        this.postNo = postNo;
        this.nicname = nicname;
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

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public boolean isUseAnonymousName() {
        return isUseAnonymousName;
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
