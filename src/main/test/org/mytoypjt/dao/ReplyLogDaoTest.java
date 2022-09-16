package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Reply;
import org.mytoypjt.utils.DBUtil;

import static org.junit.jupiter.api.Assertions.*;

class ReplyLogDaoTest {

    ReplyLogDao replyLogDao;

    @BeforeEach
    public void init(){
        // init param
        replyLogDao = new ReplyLogDao(DBUtil.getBasicDataSource());
    }

    @Test
    void writeLog() {
        boolean successed = true;

        try {
            // test content
            Reply reply = new Reply("test-content", 6, 39, false, "admin-test");
            
            for (int i=0 ; i<3 ; i++)
                replyLogDao.writeLog(reply, "생성");

            for (int i=0 ; i<3 ; i++)
                replyLogDao.writeLog(reply, "삭제");

        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }

    @Test
    void getLogsByAccountNo() {
        boolean successed = true;

        try {
            // test content
            replyLogDao.getLogsByAccountNo(6, 100).forEach(replyLog -> {
                System.out.println(replyLog.getLoggingDate());
            });
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }
}