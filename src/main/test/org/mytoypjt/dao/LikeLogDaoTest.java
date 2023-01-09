package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Like;
import org.mytoypjt.utils.DBUtil;

import static org.junit.jupiter.api.Assertions.*;

class LikeLogDaoTest {

    LikeLogDao likeLogDao;

    @BeforeEach
    public void init(){
        // init param
        likeLogDao = new LikeLogDao(DBUtil.getBasicDataSource());
    }

    @Test
    void writeLog() {
        boolean successed = true;

        try {
            // test content
            Like like = new Like(6, 15);
            for (int i=0 ; i<4 ; i++) {
                String action = i%2==0 ? "" : "좋아요";
                likeLogDao.writeLog(like, action);
                Thread.sleep(500);
            }
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
            likeLogDao.getLogsByAccountNo(6, 100).forEach(likeLog -> {
                System.out.println(likeLog.getLogMsg() + " - " + likeLog.getLoggingDate());
            });
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }
}