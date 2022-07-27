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
    boolean is_use_anonymous_city;
    boolean is_use_anonymous_name;
    int commentCount;
    int likeCount;
    int accountNo;
    int pictureNo;
    String nicname;

    public Post() {
    }

    public Post(int postNo, String title, String content, Date postedDate, boolean is_use_anonymous_city, boolean is_use_anonymous_name, int commentCount, int likeCount, int accountNo, int pictureNo, String nicname) {
        this.postNo = postNo;
        this.title = title;
        this.content = content;
        this.postedDate = postedDate;
        this.is_use_anonymous_city = is_use_anonymous_city;
        this.is_use_anonymous_name = is_use_anonymous_name;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.accountNo = accountNo;
        this.pictureNo = pictureNo;
        this.nicname = nicname;
    }

    public boolean getIs_use_anonymous_city() {
        return is_use_anonymous_city;
    }

    public boolean getIs_use_anonymous_name() {
        return is_use_anonymous_name;
    }

    public Date getPostedDate() {
        return postedDate;
    }
}
