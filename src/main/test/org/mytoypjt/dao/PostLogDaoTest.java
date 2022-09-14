package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.utils.DBUtil;

import static org.junit.jupiter.api.Assertions.*;

class PostLogDaoTest {

    PostLogDao postLogDao;

    @BeforeEach
    public void init(){
        // init param
        postLogDao = new PostLogDao(DBUtil.getBasicDataSource());
    }

    @Test
    void writeCreationLog() {

        boolean successed = true;
        try {
            // test content
            Post post = new Post();
            post.setAccountNo(19);
            post.setPostNo(101);

            postLogDao.writeLog(post, "테스트");
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}