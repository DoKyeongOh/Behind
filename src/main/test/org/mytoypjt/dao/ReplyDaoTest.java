package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.entity.Reply;
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
            profile.setNickname("hhhh");
            this.replyDao.createReply(new Reply("ddd", profile.getAccountNo(), 13, true, "testNicName"));
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void updateReply() {
        boolean successed = true;
        try {
            // test content
            Reply reply = replyDao.getReply(11);
            reply.setContent("안녕하세요 - 수정된 대댓글임 - 3");
            reply.setNameAnonymous(false);
            replyDao.updateReply(reply);
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}