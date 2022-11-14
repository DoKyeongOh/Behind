package org.mytoypjt.dao;

import org.mytoypjt.models.entity.LoginLog;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.PostLog;
import org.mytoypjt.models.entity.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class LoginLogDao {

    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;
    RowMapper<LoginLog> rowMapper;

    public LoginLogDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("post");
        rowMapper = (rs, rowNum) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
            LoginLog loginLog = new LoginLog(
                    rs.getInt("post_log_no"),
                    sdf.format(rs.getTimestamp("logging_date")),
                    rs.getString("action_type"),
                    rs.getInt("entity_no")
            );
            return loginLog;
        };
    }

    public void writeLog(Profile profile, String action) {
        String sql = "insert into login_log (post_log_no, logging_date, action_type, account_no) " +
                "values (null, now(), :actionType, :accountNo)";

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("actionType", action)
                .addValue("accountNo", profile.getAccountNo());

        jdbcTemplate.update(sql, param);
    }

    public List<LoginLog> getLogsByAccountNo(int accountNo, int count){
        String sql = "select * from login_log where account_no=:accountNo limit :count";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("accountNo", accountNo)
                .addValue("count", count);

        return jdbcTemplate.query(sql, param, rowMapper);
    }
}
