package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.DBUtil;

import static org.junit.jupiter.api.Assertions.*;

class CommentDaoTest {

    CommentDao commentDao;

    @BeforeEach
    public void init(){
        // init param
        commentDao = new CommentDao(DBUtil.getBasicDataSource());
    }

    @Test
    void createComment() {
        boolean successed = true;
        try {
            // test content
            Profile profile = new Profile(100);
            profile.setNicname("hhhh");
            Comment comment = new Comment("test - 1", 5, 15, "testName", true);
            commentDao.createComment(comment);
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void getComments() {
        boolean successed = true;
        try {
            // test content
            this.commentDao.getComments(43).forEach(comment -> System.out.println(comment.getContent()));
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void deleteComment() {
        boolean successed = true;

        try {
            // test content
            Comment comment = new Comment("testcomment", 6, 37, "테스트닉네임", false);
            comment.setCommentNo(42);
            commentDao.deleteComment(comment.getCommentNo());
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }
}