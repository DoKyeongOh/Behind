package org.mytoypjt.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostDaoTest {



    @Test
    void modifyLike() {

        boolean successed = true;

        try {
            PostDao postDao = new PostDao();
            postDao.modifyLike(3, 5, false);
        } catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }

        assertEquals(true, successed);

    }
    @Test
    void updateLikeCount() {
        boolean successed = true;

        try {
            PostDao postDao = new PostDao();
            postDao.updateLikeCount(3,2);
        } catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }

        assertEquals(true, successed);
    }

    @Test
    void getLikeCount() {
        boolean successed = true;

        try {
            PostDao postDao = new PostDao();
            int count = postDao.getLikeCount(3);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }

        assertEquals(true, successed);
    }

    @Test
    void getCommentCount() {
        boolean successed = true;

        try {
            PostDao postDao = new PostDao();
            int count = postDao.getCommentCount(3);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }

        assertEquals(true, successed);
    }
}