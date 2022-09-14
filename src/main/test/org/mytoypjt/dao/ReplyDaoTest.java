package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.DBUtil;

import static org.junit.jupiter.api.Assertions.*;

class ReplyDaoTest {

    ReplyDao replyDao;

    @BeforeEach
    public void init(){
        // init param
        replyDao = new ReplyDao(DBUtil.getBasicDataSource());
    }

    @Test
    void getReplies() {

        boolean successed = true;
        try {
            // test content
            replyDao.getReplies(1).forEach(System.out::println);
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void createReply() {

        boolean successed = true;
        try {
            // test content

            Profile profile = new Profile(100);
            profile.setNicname("hhhh");
            this.replyDao.createReply("ddd", profile, 13, true);
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}