package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.DBUtil;

import java.sql.*;

public class ProfileDao {
    public Profile getProfile(int accountNo) {
        String sql = "select * from profile where account_no=?";
        Profile profile = null;
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                profile = new Profile(
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
        return profile;
    }

    public boolean createProfile(Profile profile){
        String sql = "insert into profile(" +
                "account_no, register_date, nicname, city, age, gender, user_level) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            long timeInMilliSeconds = profile.getJoinDate().getTime();
            Date sqlDate = new Date(timeInMilliSeconds);

            preparedStatement.setInt(1, profile.getAccountNo());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setString(3, profile.getNicname());
            preparedStatement.setString(4, profile.getCity());
            preparedStatement.setInt(5, profile.getAge());
            preparedStatement.setString(6, profile.getGender());
            preparedStatement.setInt(7, profile.getUserLevel());
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateProfile(Profile profile){
        String sql = "update profile set nicname=?, city=?, age=?, gender=?, user_level=? " +
                "where account_no=?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, profile.getNicname());
            preparedStatement.setString(2, profile.getCity());
            preparedStatement.setInt(3, profile.getAge());
            preparedStatement.setString(4, profile.getGender());
            preparedStatement.setInt(5, profile.getUserLevel());
            preparedStatement.setInt(6, profile.getAccountNo());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
