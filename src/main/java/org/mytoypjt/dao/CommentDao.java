package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Account;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.DBUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CommentDao {

    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;
    RowMapper<Comment> commentRowMapper;

    public CommentDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("comment");
        commentRowMapper = (rs, rowNum) -> {
            Comment comment = new Comment(
                    rs.getInt("comment_no"),
                    rs.getString("content"),
                    rs.getInt("reply_count"),
                    rs.getInt("account_no"),
                    rs.getInt("post_no"),
                    rs.getBoolean("is_use_anonymous_name"),
                    rs.getString("nicname"),
                    rs.getDate("commented_date")
            );
            return comment;
        };

    }

    public List<Comment> getComments(int postNo) {
        String sql = "select " +
                "comment_no, content, reply_count, account_no, post_no, is_use_anonymous_name, nicname, commented_date" +
                " from comment where post_no = :postNo";

        try {
            return jdbcTemplate.query(sql, new MapSqlParameterSource("postNo", postNo), commentRowMapper);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void createComment(int postNo, Profile profile, boolean isAnonymous, String content) {
        String sql = "insert into comment " +
                "(comment_no, content, reply_count, account_no, post_no, is_use_anonymous_name, nicname, commented_date) " +
                "values (null , ?, 0, ?, ?, ?, ?, now())";

        SqlParameterSource param = new BeanPropertySqlParameterSource(new Comment(
                content, 0, profile.getAccountNo(), postNo, profile.getNicname(), isAnonymous, new Date()
        ));

        jdbcInsert.execute(param);
    }

    public int getCommentCount(int postNo) {
        String sql = "select count(*) from comment where post_no=:postNo";

        int count = jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("postNo", postNo),
                (rs, rowNum) -> rs.getInt(1));
        return count;
    }

    public void updateCommentCount(int postNo, int count){
        String sql = "update post set comment_count = :commentCount where post_no = :postNo";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("commentCount", count);
        param.addValue("postNo", postNo);

        jdbcTemplate.update(sql, param);
    }

    public Comment getCommentByCommentNo(int commentNo) {
        String sql = "select * from comment where comment_no = :commentNo";

        MapSqlParameterSource param = new MapSqlParameterSource("commentNo", commentNo);

        return jdbcTemplate.query(sql, param, commentRowMapper).get(0);
    }

    public List<Comment> getCommentsByAccountNo(int accountNo) {
        String sql = "select * from comment where account_no = :accountNo";

        MapSqlParameterSource param = new MapSqlParameterSource("accountNo", accountNo);
        return jdbcTemplate.query(sql, param, commentRowMapper);
    }
}
