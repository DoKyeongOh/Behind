package org.mytoypjt.dao;

import org.mytoypjt.entity.User;
import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
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
