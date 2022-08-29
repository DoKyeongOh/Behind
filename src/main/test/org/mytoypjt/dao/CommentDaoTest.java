package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void getCommentsByAccountNo() {

        boolean successed = true;
        try {
            // test content
            System.out.println(commentDao.getCommentsByAccountNo(19).size());
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void createComment() {
        boolean successed = true;
        try {
            // test content
            Profile profile = new Profile(100);
            profile.setNicname("hhhh");
            commentDao.createComment(40, profile, false, "test");
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
}