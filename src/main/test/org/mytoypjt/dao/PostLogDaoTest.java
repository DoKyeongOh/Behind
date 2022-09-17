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
            post.setAccountNo(6);
            post.setPostNo(101);

            for (int i=0 ; i<3 ; i++) {
                postLogDao.writeLog(post, "생성");
                Thread.sleep(1000);
            }

            for (int i=0 ; i<3 ; i++) {
                postLogDao.writeLog(post, "삭제");
                Thread.sleep(1000);
            }

        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}