package org.mytoypjt.models.entity;

public class Comment {

    public Comment() {
    }

    int commentId;
    String content;
    int reply_count;
    boolean isUseAnonymousName;
    int accountNo;
    int postId;
    String nicname;

    public Comment(int commentId, String content, int reply_count, boolean isUseAnonymousName, int accountNo, int postId, String nicname) {
        this.commentId = commentId;
        this.content = content;
        this.reply_count = reply_count;
        this.isUseAnonymousName = isUseAnonymousName;
        this.accountNo = accountNo;
        this.postId = postId;
        this.nicname = nicname;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReply_count() {
        return reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }

    public boolean getisUseAnonymousName() {
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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
}
