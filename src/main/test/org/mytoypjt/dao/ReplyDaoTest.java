package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplyDaoTest {

    ReplyDao replyDao;

    @BeforeEach
    public void init(){
        // init param
        replyDao = new ReplyDao();
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
}