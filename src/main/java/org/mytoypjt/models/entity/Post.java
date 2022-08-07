package org.mytoypjt.models.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Post {

    int postNo;
    String title;
    String content;
    Date postedDate;
    int commentCount;
    int likeCount;
    int accountNo;
    int pictureNo;
    boolean isUseAnonymousName;
    boolean isUseAnonymousCity;
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
        this.isUseAnonymousName = false;
        this.isUseAnonymousCity = false;
        this.nicname = "nicname";
        this.city = "city";
    }

    public Post(int postNo, String title, String content, Date postedDate, int commentCount, int likeCount, int accountNo,
                int pictureNo, boolean isUseAnonymousName, boolean isUseAnonymousCity,String nicname, String city) {
        this.postNo = postNo;
        this.title = title;
        this.content = content;
        this.postedDate = postedDate;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.accountNo = accountNo;
        this.pictureNo = pictureNo;
        this.isUseAnonymousName = isUseAnonymousName;
        this.isUseAnonymousCity = isUseAnonymousCity;
        this.nicname = nicname;
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

    public boolean getIsUseAnonymousName() {
        return isUseAnonymousName;
    }

    public boolean getIsUseAnonymousCity() {
        return isUseAnonymousCity;
    }
}
