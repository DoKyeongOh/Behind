package org.mytoypjt.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilTest {

    @Test
    void getConnection(){
        boolean successed = true;
        try {
            DBUtil dbUtil = DBUtil.getInstance();
            Connection connection = dbUtil.getConnection();

        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void getConnectionByDataSource() {

        boolean successed = true;
        try {
            // test content
            List<Connection> connections = new ArrayList<>();
            DataSource dataSource = DBUtil.getBasicDataSource();
            for (int i=0 ; i<3 ; i++) {
                connections.add(dataSource.getConnection());
//                connections.get(i).close();
            }
            connections.forEach(connection -> System.out.println(connection.hashCode() + "생성"));
            connections.forEach(connection -> {
                System.out.println(connection.hashCode() + "삭제");
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }

        assertEquals(true, successed);
    }
}