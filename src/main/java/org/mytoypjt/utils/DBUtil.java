package org.mytoypjt.utils;

import java.sql.*;

public class DBUtil {

    String url = "jdbc:mysql://localhost:3306/behind?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    String id="behind_admin";
    String pw="1234";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, id, pw);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



}
