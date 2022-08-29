package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Account;
import org.mytoypjt.utils.DBUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
        MapSqlParameterSource param = new MapSqlParameterSource("email", email);
        return jdbcTemplate.query(sql, param, (rs, rowNum) -> rs.getString("id"));
    }

    public int findAccountNo(String id, String email){
        String sql = "select account_no from account where id=:id and email=:email";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        param.addValue("email", email);

        List<Account> accounts = jdbcTemplate.query(sql, param, accountRowMapper);
        if (accounts.size() > 2 || accounts.size() < 1)
            return -1;

        return accounts.get(0).getAccountNo();
    }

    public int findAccountNo(String id, String password, String email){
        String sql = "select account_no from account where id=:id and password=:password and email=:email";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        param.addValue("password", password);
        param.addValue("email", email);

        List<Account> accounts = jdbcTemplate.query(sql, param, accountRowMapper);
        if (accounts.size() > 2 || accounts.size() < 1)
            return -1;

        return accounts.get(0).getAccountNo();
    }

    public int findAccountNo(Account account){
        String id = account.getId();
        String password = account.getPassword();
        String email = account.getEmail();

        int accountNo = findAccountNo(id, password, email);
        return accountNo;
    }

    public boolean setAccountPw(int accountNo, String password){
        String sql = "update account set password = :password where account_no = :accountNo";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("password", password);
        param.addValue("accountNo", accountNo);
        if (jdbcTemplate.update(sql, param) == 1) return true;
        return false;
    }

    public boolean isExistId(String id){
        String sql = "select account_no from account where id=:id";
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        if (jdbcTemplate.query(sql, param, accountRowMapper).size() > 0)
            return true;
        return false;
    }

    public boolean createAccount(Account account){
        SqlParameterSource param = new BeanPropertySqlParameterSource(account);
        int returnValue = jdbcInsert.execute(param);
        if (returnValue == 1)
            return true;
        return false;
    }

    public void deleteAccount(int accountNo){
        String sql = "delete from account where account_no=:accountNo";

        Map<String, Integer> param = new HashMap<>();
        param.put("accountNo", accountNo);
        jdbcTemplate.update(sql, param);
    }

    public boolean isRegisteredEmail(String email) {
        String sql = "select id from account where email=:email";
        MapSqlParameterSource param = new MapSqlParameterSource("email", email);
        int size = jdbcTemplate.query(sql, param, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("id");
            }
        }).size();

        if (size > 0) return true;
        return false;
    }
}
