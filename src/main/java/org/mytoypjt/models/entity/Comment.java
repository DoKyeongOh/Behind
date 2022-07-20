package org.mytoypjt.models.entity;

public class Comment {

    public Comment() {
    }

    public Comment(int commentId, String content, int reply_count, boolean isUseAnonymousName, int accountNo, int postId) {
        this.commentId = commentId;
        this.content = content;
        this.reply_count = reply_count;
        this.isUseAnonymousName = isUseAnonymousName;
        this.accountNo = accountNo;
        this.postId = postId;
    }

    int commentId;
    String content;
    int reply_count;
    boolean isUseAnonymousName;
    int accountNo;
    int postId;

}
