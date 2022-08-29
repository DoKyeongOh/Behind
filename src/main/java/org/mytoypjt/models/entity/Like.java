package org.mytoypjt.models.entity;

public class Like {
    int likeNo;
    int accountNo;
    int postNo;

    public Like(int likeNo, int accountNo, int postNo) {
        this.likeNo = likeNo;
        this.accountNo = accountNo;
        this.postNo = postNo;
    }

    public Like(int accountNo, int postNo) {
        this.accountNo = accountNo;
        this.postNo = postNo;
    }

    public int getLikeNo() {
        return likeNo;
    }

    public void setLikeNo(int likeNo) {
        this.likeNo = likeNo;
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
}
