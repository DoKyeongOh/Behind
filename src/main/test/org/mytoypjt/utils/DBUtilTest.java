package org.mytoypjt.utils;

import org.apache.commons.dbcp2.PoolingDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilTest {

    DBUtil dbUtil;

    @BeforeEach
    public void init(){
        // init param
        dbUtil = DBUtil.getInstance();
    }

    @Test
    void getConnection(){
        boolean successed = true;
        try (
                Connection conn = DBUtil.getInstance().getConnection();
                ) {
            // test content
            System.out.println(conn);
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void createConnectionPool(){

        boolean successed = true;
        try {
            // test content
            dbUtil.createConnectionPool();
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

}