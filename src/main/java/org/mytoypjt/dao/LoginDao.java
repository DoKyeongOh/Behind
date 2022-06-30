package org.mytoypjt.dao;

import org.mytoypjt.entity.User;
import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    final int NOT_CORRECTED_USER_NO = -1;
    final int DUPLICATION_ACCOUNT = -2;

    public int getUserId(String id, String pw) {
        String sql = "select account_no from account where id=? and password=?";
        int userNo = NOT_CORRECTED_USER_NO;
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, pw);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (userNo != NOT_CORRECTED_USER_NO) return DUPLICATION_ACCOUNT;
                userNo = resultSet.getInt(1);
            }
            return userNo;
        } catch (SQLException e) {
            e.printStackTrace();
            return NOT_CORRECTED_USER_NO;
        }
    }

    public User getUser(int accountNo) {
        String sql = "select * from user where account_no=?";
        User user = null;
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User(
                        resultSet.getInt("account_no"),
                        resultSet.getString("nicname"),
                        resultSet.getDate("register_date"),
                        resultSet.getString("city"),
                        resultSet.getInt("age"),
                        resultSet.getString("gender"),
                        resultSet.getInt("user_level")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
