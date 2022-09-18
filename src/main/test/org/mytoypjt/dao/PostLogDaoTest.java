package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.PostLog;
import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    void getLogsByAccountNo(){
        boolean successed = true;

        try {
            // test content
            postLogDao.getLogsByAccountNo(6, 100).forEach(log -> {
                System.out.println(log.getLoggingDate());
            });
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }

    @Test
    void test(){
        boolean successed = true;

        try {
            // test content
            Date date = new Date();
            System.out.println(date.toString());

        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }
}