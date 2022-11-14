package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.dao.log.CommentLogDao;
import org.mytoypjt.dao.log.LikeLogDao;
import org.mytoypjt.dao.log.PostLogDao;
import org.mytoypjt.dao.log.ReplyLogDao;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Like;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Reply;
import org.mytoypjt.utils.DBUtil;

import static org.junit.Assert.assertEquals;

public class TotalDaoTest {

    PostLogDao postLogDao;
    CommentLogDao commentLogDao;
    ReplyLogDao replyLogDao;
    LikeLogDao likeLogDao;

    @BeforeEach
    public void init(){
        // init param
        postLogDao = new PostLogDao(DBUtil.getBasicDataSource());
        commentLogDao = new CommentLogDao(DBUtil.getBasicDataSource());
        replyLogDao = new ReplyLogDao(DBUtil.getBasicDataSource());
        likeLogDao = new LikeLogDao(DBUtil.getBasicDataSource());
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

            String good = "";
            for (int i=0 ; i<10 ; i++) {
                randomValue = i % 4;
                switch (randomValue){
                    case 0:postLogDao.writeLog(post, "수정"); break;
                    case 1:commentLogDao.writeLog(comment, "수정"); break;
                    case 2:replyLogDao.writeLog(reply, "수정"); break;
                    case 3: {
                        good = good.equals("좋아요") ? "" : "좋아요";
                        likeLogDao.writeLog(new Like(6, 15), good);
                        break;
                    }
                }
                Thread.sleep(500);
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
            String answer = "1234";
            String[] items = answer.split("");
            answer = "";
            for (int i=0 ; i<4 ; i++)
                answer = answer + items[i] + " ";
            answer = answer.substring(0, answer.length()-1);
            answer += "!";
            answer += 'f';
            answer.charAt(answer.length()-1);
            System.out.println(answer);

        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }

}
