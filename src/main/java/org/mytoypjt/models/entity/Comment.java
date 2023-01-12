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
    String nickname;
    boolean nameAnonymous;
    Date commentedDate;


    public Comment(int commentNo) {
        this.commentNo = commentNo;
        this.content = "";
        this.replyCount = 0;
        this.accountNo = -1;
        this.postNo = -1;
        this.nameAnonymous = false;
        this.nickname = "";
        this.commentedDate = new Date();
    }

    public Comment(int commentNo, String content, int replyCount, int accountNo, int postNo, boolean nameAnonymous,String nickname, Date commentedDate) {
        this.commentNo = commentNo;
        this.content = content;
        this.replyCount = replyCount;
        this.accountNo = accountNo;
        this.postNo = postNo;
        this.nameAnonymous = nameAnonymous;
        this.nickname = nickname;
        this.commentedDate = commentedDate;
    }

    public Comment(String content, int replyCount, int accountNo, int postNo, String nickname, boolean nameAnonymous, Date commentedDate) {
        this.content = content;
        this.replyCount = replyCount;
        this.accountNo = accountNo;
        this.postNo = postNo;
        this.nickname = nickname;
        this.nameAnonymous = nameAnonymous;
        this.commentedDate = commentedDate;
    }

    public Comment(String content, int accountNo, int postNo, String nickname, boolean nameAnonymous) {
        this.content = content;
        this.postNo = postNo;
        this.nickname = nickname;
        this.nameAnonymous = nameAnonymous;
        this.replyCount = 0;
        this.accountNo = accountNo;
        this.commentedDate = new Date();
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

    public String getnickname() {
        return nickname;
    }

    public void setnickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isNameAnonymous() {
        return nameAnonymous;
    }

    public void setNameAnonymous(boolean nameAnonymous) {
        this.nameAnonymous = nameAnonymous;
    }

    public Date getCommentedDate() {
        return commentedDate;
    }

    public void setCommentedDate(Date commentedDate) {
        this.commentedDate = commentedDate;
    }
}
