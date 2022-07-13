package org.mytoypjt.dao;

import org.mytoypjt.models.entity.User;
import org.mytoypjt.utils.DBUtil;

import java.sql.*;

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

    public boolean createUser(User user){
        String sql = "insert into user(" +
                "account_no, register_date, nicname, city, age, gender, user_level) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            long timeInMilliSeconds = user.getJoinDate().getTime();
            Date sqlDate = new Date(timeInMilliSeconds);

            preparedStatement.setInt(1, user.getAccountNo());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setString(3, user.getNicname());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setInt(7, user.getUserLevel());
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateUser(User user){
        String sql = "update user set nicname=?, city=?, age=?, gender=?, user_level=? " +
                "where account_no=?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, user.getNicname());
            preparedStatement.setString(2, user.getCity());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getGender());
            preparedStatement.setInt(5, user.getUserLevel());
            preparedStatement.setInt(6, user.getAccountNo());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
