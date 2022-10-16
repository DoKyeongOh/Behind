package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.utils.DBUtil;

import java.awt.image.DataBuffer;

import static org.junit.jupiter.api.Assertions.*;

class CommentLogDaoTest {

    CommentLogDao commentLogDao;

    @BeforeEach
    public void init(){
        // init param
        commentLogDao = new CommentLogDao(DBUtil.getBasicDataSource());
    }

    @Test
    void writeLog() {
        boolean successed = true;

        try {
            // test content
            Comment comment = new Comment("testcomment", 6, 37, "테스트닉네임", false);
            comment.setCommentNo(42);
            this.commentLogDao.writeLog(comment, "테스트");

            
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }

    @Test
    void getLogsByAccountNo() {
        boolean successed = true;

        try {
            // test content
            commentLogDao.getLogsByAccountNo(6, 100).forEach(commentLog -> {
                System.out.println(commentLog.getLogMsg() + " - " + commentLog.getLoggingDate());
            });
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }

    @Test
    void testWriteLog() {
    }
}