package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Account;
import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {
    final int NOT_CORRECTED_ACCOUNT_NO = -1;
    final int DUPLICATION_ACCOUNT = -2;
    final String NOT_FOUND_ACCOUNT_ID = "";

    public int getAccountNo(String id, String pw) {
        String sql = "select account_no from account where id=? and password=?";
        int accountNo = NOT_CORRECTED_ACCOUNT_NO;
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, pw);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (accountNo != NOT_CORRECTED_ACCOUNT_NO) return DUPLICATION_ACCOUNT;
                accountNo = resultSet.getInt(1);
            }
            return accountNo;
        } catch (SQLException e) {
            e.printStackTrace();
            return NOT_CORRECTED_ACCOUNT_NO;
        }
    }

    public String getAccountNoByEmail(String email){
        String sql = "select id from account where email=?";
        String id = NOT_FOUND_ACCOUNT_ID;

        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (id != NOT_FOUND_ACCOUNT_ID) return NOT_FOUND_ACCOUNT_ID;
                id = resultSet.getString("id");
            }

            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return NOT_FOUND_ACCOUNT_ID;
        }
    }

    public int findAccountNo(String id, String email){
        String sql = "select account_no from account where id=? and email=?";
        int accountNo = NOT_CORRECTED_ACCOUNT_NO;
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (accountNo != NOT_CORRECTED_ACCOUNT_NO) return DUPLICATION_ACCOUNT;
                accountNo = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountNo;
    }

    public int findAccountNo(String id, String password, String email){
        String sql = "select account_no from account where id=? and password=? and email=?";
        int accountNo = NOT_CORRECTED_ACCOUNT_NO;
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (accountNo != NOT_CORRECTED_ACCOUNT_NO) return DUPLICATION_ACCOUNT;
                accountNo = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountNo;
    }

    public int findAccountNo(Account account){
        String id = account.getId();
        String password = account.getPassword();
        String email = account.getEmail();

        int accountNo = findAccountNo(id, password, email);
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
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public boolean createAccount(Account account){
        String id = account.getId();
        String password = account.getPassword();
        String email = account.getEmail();

        String sql = "insert into account(account_no, id, password, email) " +
                "values " +
                "(null, ?, ?, ?)";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void deleteAccount(int accountNo){
        String sql = "delete from account where account_no=?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, accountNo);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
