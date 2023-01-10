package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Account;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountDao {
    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;
    RowMapper<Account> accountRowMapper;


    public AccountDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("account")
                .usingGeneratedKeyColumns("account_no");
        accountRowMapper = (rs, rowNum) -> {
            Account account = new Account(
                    rs.getInt("account_no"),
                    rs.getString("id"),
                    rs.getString("password"),
                    rs.getString("email")
            );

            return account;
        };
    }

    public List<Account> findAccountByIdAndPw(String id, String pw) {
        String sql = "select * from account where id=:id and password=:password";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id", id);
        sqlParameterSource.addValue("password", pw);
        return jdbcTemplate.query(sql, sqlParameterSource, accountRowMapper);
    }

    public List<Account> findByEmail(String email){
        String sql = "select * from account where email=:email";
        MapSqlParameterSource param = new MapSqlParameterSource("email", email);
        return jdbcTemplate.query(sql, param, accountRowMapper);
    }

    public List<Account> findByIdAndEmail(String id, String email){
        String sql = "select * from account where id=:id and email=:email";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        param.addValue("email", email);
        return jdbcTemplate.query(sql, param, accountRowMapper);
    }

    public void setAccountPw(int accountNo, String password){
        String sql = "update account set password = :password where account_no = :accountNo";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("password", password);
        param.addValue("accountNo", accountNo);
        jdbcTemplate.update(sql, param);
    }

    public boolean isExistId(String id){
        String sql = "select * from account where id=:id";
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        return jdbcTemplate.query(sql, param, accountRowMapper).size() > 0;
    }

    public int createAccount(Account account){
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("account_no", account.getAccountNo())
                .addValue("id", account.getId())
                .addValue("password", account.getPassword())
                .addValue("email", account.getEmail());
        BigInteger bigInteger = (BigInteger) jdbcInsert.executeAndReturnKey(param);
        return bigInteger.intValue();
    }

    public void deleteAccount(int accountNo){
        String sql = "delete from account where account_no=:accountNo";

        Map<String, Integer> param = new HashMap<>();
        param.put("accountNo", accountNo);
        try {
            jdbcTemplate.update(sql, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isRegisteredEmail(String email) {
        String sql = "select id from account where email=:email";
        MapSqlParameterSource param = new MapSqlParameterSource("email", email);
        try {
            return jdbcTemplate.query(sql, param, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString("id");
                }
            }).size() > 0;
        } catch (Exception e) {
            return true;
        }

    }
}
