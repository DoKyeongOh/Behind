package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.entity.Reply;
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
import java.util.List;

@Repository
public class ReplyDao {

    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;
    RowMapper<Reply> replyRowMapper;

    public ReplyDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("profile");
        replyRowMapper = (rs, rowNum) -> {
            Reply reply = new Reply(
                    rs.getInt("reply_no"),
                    rs.getString("content"),
                    rs.getInt("account_no"),
                    rs.getInt("comment_no"),
                    rs.getBoolean("name_anonymous"),
                    rs.getString("nicname"),
                    rs.getDate("replied_date")
            );
            return reply;
        };

    }

    public List<Reply> getReplies(int commentNo) {
        String sql = "select * from reply where comment_no = :commentNo";

        MapSqlParameterSource param = new MapSqlParameterSource("commentNo", commentNo);
        return jdbcTemplate.query(sql, param, replyRowMapper);
    }

    public void createReply(String content, Profile profile, int commentNoInt, boolean isAnonName) {
        String sql = "insert into reply " +
                "(reply_no, content, account_no, comment_no, is_use_anonymous_name, nicname, replied_date)" +
                "values (null, :content, :accountNo, :commentNo, :nameAnonymous, :nicname, now())";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("content", content);
        param.addValue("accountNo", profile.getAccountNo());
        param.addValue("commentNo", commentNoInt);
        param.addValue("nameAnonymous", isAnonName);
        param.addValue("nicname", profile.getNicname());

        jdbcTemplate.update(sql, param);
    }

}
