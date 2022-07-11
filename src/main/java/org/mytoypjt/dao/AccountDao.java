package org.mytoypjt.dao;

import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {
    final int NOT_CORRECTED_USER_NO = -1;
    final int DUPLICATION_ACCOUNT = -2;
    final String NOT_FOUND_USER_ID = "";

    public int getAccountNo(String id, String pw) {
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


    public String getAccountNoByEmail(String email){
        String sql = "select id from account where email=?";
        String userId = NOT_FOUND_USER_ID;

        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (userId != NOT_FOUND_USER_ID) return NOT_FOUND_USER_ID;
                userId = resultSet.getString("id");
            }

            return userId;
        } catch (SQLException e) {
            e.printStackTrace();
            return NOT_FOUND_USER_ID;
        }
    }

    public int findAccountNo(String id, String email){
        String sql = "select account_no from account where id=? and email=?";
        int accountNo = NOT_CORRECTED_USER_NO;
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (accountNo != NOT_CORRECTED_USER_NO) return DUPLICATION_ACCOUNT;
                accountNo = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountNo;
    }

    public boolean setAccountPw(int accountNo, String password){
        String sql = "update account set password = ? where account_no = ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, accountNo);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isExistId(String id){
        String sql = "select account_no from account where id=?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();

            int accountNo = -1;

            while (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

}
