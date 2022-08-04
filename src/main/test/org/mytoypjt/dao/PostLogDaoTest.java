package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.PostLog;

import static org.junit.jupiter.api.Assertions.*;

class PostLogDaoTest {

    PostLogDao postLogDao;

    @BeforeEach
    public void init(){
        // init param
        postLogDao = new PostLogDao();
    }

    @Test
    void writeCreationLog() {

        boolean successed = true;
        try {
            // test content
            postLogDao.writeCreationLog(19, 25);
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}