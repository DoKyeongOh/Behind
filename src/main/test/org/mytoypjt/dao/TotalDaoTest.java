package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Reply;
import org.mytoypjt.utils.DBUtil;

import static org.junit.Assert.assertEquals;

public class TotalDaoTest {

    PostLogDao postLogDao;
    CommentLogDao commentLogDao;
    ReplyLogDao replyLogDao;

    @BeforeEach
    public void init(){
        // init param
        postLogDao = new PostLogDao(DBUtil.getBasicDataSource());
        commentLogDao = new CommentLogDao(DBUtil.getBasicDataSource());
        replyLogDao = new ReplyLogDao(DBUtil.getBasicDataSource());
    }

    @Test
    void logCreateTest(){
        boolean successed = true;

        try {
            // test content
            int randomValue;

            Post post = new Post();
            post.setAccountNo(6);
            post.setPostNo(15);

            Comment comment =  new Comment("test-title", 6, 15, "admin-test", false);
            Reply reply =  new Reply("test-content", 6, 39, false, "admin-test");


            for (int i=0 ; i<5 ; i++) {
                randomValue = i % 3;
                switch (randomValue){
                    case 0:postLogDao.writeLog(post, "수정"); break;
                    case 1:commentLogDao.writeLog(comment, "수정"); break;
                    case 2:replyLogDao.writeLog(reply, "수정"); break;
                }
                Thread.sleep(1000);
            }

        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }

    @Test
    void logInitTest(){
        boolean successed = true;

        try {
            // test content
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }

}
