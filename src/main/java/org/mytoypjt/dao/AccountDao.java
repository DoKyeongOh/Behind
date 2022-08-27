package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Account;
import org.mytoypjt.utils.DBUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountDao {
    final int NOT_CORRECTED_ACCOUNT_NO = -1;
    final int DUPLICATION_ACCOUNT = -2;

    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;
    RowMapper<Account> accountRowMapper;


    public AccountDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("account");
        accountRowMapper = new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account account = new Account(
                        rs.getInt("account_no"),
                        rs.getString("id"),
                        rs.getString("password"),
                        rs.getString("email")
                );
                return account;
            }
        };
    }

    public int getAccountNo(String id, String pw) {
        String sql = "select * from account where id=:id and password=:password";

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id", id);
        sqlParameterSource.addValue("password", pw);

        List<Account> accountList = jdbcTemplate.query(sql, sqlParameterSource, accountRowMapper);
        if (accountList.size() > 1)
            return -1;

        return accountList.get(0).getAccountNo();
    }

    public List<String> getAccountListByEmail(String email){
        String sql = "select id from account where email=:email";

        MapSqlParameterSource msps = new MapSqlParameterSource("email", email);

        return jdbcTemplate.query(sql, msps, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("id");
            }
        });

        /*try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                idList.add(resultSet.getString("id"));
            }
            return idList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }*/
    }

    public int findAccountNo(String id, String email){
        String sql = "select account_no from account where id=? and email=?";
        int accountNo = NOT_CORRECTED_ACCOUNT_NO;

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setInt(1, accountNo);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isRegisteredEmail(String email) {
        String sql = "select id from account where email=?";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
