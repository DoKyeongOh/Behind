package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentDaoTest {

    CommentDao commentDao;

    @BeforeEach
    public void init(){
        // init param
        commentDao = new CommentDao();
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
}