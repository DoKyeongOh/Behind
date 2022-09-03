package org.mytoypjt.models.entity;

import java.util.Date;

public class Post {

    int postNo;
    String title;
    String content;
    Date postedDate;
    int commentCount;
    int likeCount;
    int accountNo;
    int pictureNo;
    boolean nameAnonymous;
    boolean cityAnonymous;
    String nicname;
    String city;

    public Post() {
        this.postNo = -1;
        this.title = "title";
        this.content = "content";
        this.postedDate = new Date();
        this.commentCount = 0;
        this.likeCount = 0;
        this.accountNo = -1;
        this.pictureNo = 1;
        this.nameAnonymous = false;
        this.cityAnonymous = false;
        this.nicname = "nicname";
        this.city = "city";
    }

    public Post(int postNo, String title, String content, Date postedDate, int commentCount, int likeCount, int accountNo,
                int pictureNo, boolean nameAnonymous, boolean cityAnonymous, String nicname, String city) {
        this.postNo = postNo;
        this.title = title;
        this.content = content;
        this.postedDate = postedDate;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.accountNo = accountNo;
        this.pictureNo = pictureNo;
        this.nameAnonymous = nameAnonymous;
        this.cityAnonymous = cityAnonymous;
        this.nicname = nicname;
        this.city = city;
    }

    public Post(String title, String content, Date postedDate, int commentCount, int likeCount, int accountNo, int pictureNo, boolean nameAnonymous, boolean cityAnonymous, String nicname, String city) {
        this.title = title;
        this.content = content;
        this.postedDate = postedDate;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.accountNo = accountNo;
        this.pictureNo = pictureNo;
        this.nameAnonymous = nameAnonymous;
        this.cityAnonymous = cityAnonymous;
        this.nicname = nicname;
        this.city = city;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public int getPictureNo() {
        return pictureNo;
    }

    public void setPictureNo(int pictureNo) {
        this.pictureNo = pictureNo;
    }

    public boolean isNameAnonymous() {
        return nameAnonymous;
    }

    public void setNameAnonymous(boolean nameAnonymous) {
        this.nameAnonymous = nameAnonymous;
    }

    public boolean isCityAnonymous() {
        return cityAnonymous;
    }

    public void setCityAnonymous(boolean cityAnonymous) {
        this.cityAnonymous = cityAnonymous;
    }

    public String getNicname() {
        return nicname;
    }

    public void setNicname(String nicname) {
        this.nicname = nicname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static boolean isCorrectPost(Post post){
        if (post == null) return false;
        if (post.getAccountNo() < 1) return false;
        if (post.getLikeCount() < 0) return false;
        if (post.getCommentCount() < 0) return false;
        return true;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public boolean getnameAnonymous() {
        return nameAnonymous;
    }

    public boolean getcityAnonymous() {
        return cityAnonymous;
    }
}
