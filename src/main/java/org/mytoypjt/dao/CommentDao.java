package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Comment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CommentDao {

    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;
    RowMapper<Comment> commentRowMapper;

    public CommentDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("comment").usingGeneratedKeyColumns("comment_no");
        commentRowMapper = (rs, rowNum) -> {
            Comment comment = new Comment(
                    rs.getInt("comment_no"),
                    rs.getString("content"),
                    rs.getInt("reply_count"),
                    rs.getInt("account_no"),
                    rs.getInt("post_no"),
                    rs.getBoolean("name_anonymous"),
                    rs.getString("nicname"),
                    rs.getDate("commented_date")
            );
            return comment;
        };

    }

    public List<Comment> getComments(int postNo) {
        String sql = "select " +
                "comment_no, content, reply_count, account_no, post_no, name_anonymous, nicname, commented_date" +
                " from comment where post_no = :postNo";
        return jdbcTemplate.query(sql, new MapSqlParameterSource("postNo", postNo), commentRowMapper);
    }

    public int createComment(Comment comment) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
        return jdbcInsert.executeAndReturnKey(param).intValue();
    }

    public void deleteComment(int commentNo) {
        String sql = "delete from comment where comment_no = :commentNo";
        MapSqlParameterSource param =
                new MapSqlParameterSource("commentNo", commentNo);
        jdbcTemplate.update(sql, param);
    }

    public void updateComment(Comment comment) {
        String sql = "update comment set content=:content, name_anonymous:=:nameAnonymous, nicname=:nicname " +
                "where comment_no=:commentNo";
        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
        jdbcTemplate.update(sql, param);
    }

    public int getCommentCount(int postNo) {
        String sql = "select count(*) from comment where post_no=:postNo";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("postNo", postNo),
                (rs, rowNum) -> rs.getInt(1));
    }

    public Comment getCommentByCommentNo(int commentNo) {
        String sql = "select * from comment where comment_no = :commentNo";
        MapSqlParameterSource param = new MapSqlParameterSource("commentNo", commentNo);
        return jdbcTemplate.query(sql, param, commentRowMapper).get(0);
    }

    public void updateReplyCount(int commentNo, int replyCount) {
        String sql = "update comment set reply_count = :replyCount where comment_no = :commentNo";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("replyCount", replyCount);
        param.addValue("commentNo", commentNo);
        jdbcTemplate.update(sql, param);
    }
}
