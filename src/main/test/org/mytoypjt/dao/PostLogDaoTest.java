package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostLogDaoTest {

    PostLogDao postLogDao;

    @BeforeEach
    public void init(){
        // init param
//        postLogDao = new PostLogDao();
    }

    @Test
    void writeCreationLog() {

        boolean successed = true;
        try {
            // test content
            postLogDao.writePostActivityLog(19, 25, "삭제");
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}