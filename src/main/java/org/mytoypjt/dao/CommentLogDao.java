package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.CommentLog;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CommentLogDao {

    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;
    RowMapper<CommentLog> rowMapper;

    public CommentLogDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("comment_log");
        rowMapper = (rs, rowNum) -> {
            CommentLog commentLog = new CommentLog(
                    rs.getInt("comment_log_no"),
                    rs.getTimestamp("logging_date"),
                    rs.getString("action_type"),
                    rs.getInt("account_no"),
                    rs.getInt("entity_no")
            );
            return commentLog;
        };
    }

    public void writeLog(Comment comment, String action) {
        String sql = "insert into comment_log (comment_log_no, logging_date, action_type, account_no, entity_no) " +
                "values (null, now(), :actionType, :accountNo, :entityNo)";

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("actionType", action)
                .addValue("accountNo", comment.getAccountNo())
                .addValue("entityNo", comment.getCommentNo());

        jdbcTemplate.update(sql, param);
    }

    public List<CommentLog> getLogsByAccountNo(int accountNo, int count){
        String sql = "select * from comment_log where account_no=:accountNo order by logging_date desc limit :count";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("accountNo", accountNo)
                .addValue("count", count);

        return jdbcTemplate.query(sql, param, rowMapper);
    }

}
