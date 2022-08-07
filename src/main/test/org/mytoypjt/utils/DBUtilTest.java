package org.mytoypjt.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilTest {

    @Test
    void getConnection(){
        boolean successed = true;
        try (
                BasicDataSource basicDataSource = (BasicDataSource) DBUtil.getBasicDataSource();
                ){

            long start;
            long end;
            int loofCount = 40;

            List<Integer> dsList = new ArrayList<>();
            for (int i=0 ; i<loofCount ; i++) {
                start = System.currentTimeMillis();
                Connection conn1 = basicDataSource.getConnection();
                end = System.currentTimeMillis() - start;
                dsList.add((int)end);
            }
            int sum = dsList.stream().mapToInt(i -> i).sum();
            System.out.println((float) sum/dsList.size());

            System.out.println();
            System.out.println("========================================");
            System.out.println();

            List<Integer> ncList = new ArrayList<>();
            DBUtil dbUtil = new DBUtil();
            for (int i=0 ; i<loofCount ; i++) {
                start = System.currentTimeMillis();
                Connection conn4 = dbUtil.getConnection();
                end = System.currentTimeMillis() - start;
                ncList.add((int)end);
            }
            sum = ncList.stream().mapToInt(i -> i).sum();
            System.out.println((float) sum/ncList.size());

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