package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.PostLog;
import org.mytoypjt.utils.DBUtil;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class PostLogDao {

    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;
    RowMapper<PostLog> postLogRowMapper;

    public PostLogDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("post");
        postLogRowMapper = (rs, rowNum) -> {
            PostLog postLog = new PostLog(
                    rs.getInt("post_log_no"),
                    rs.getDate("logging_date"),
                    rs.getString("action_type"),
                    rs.getInt("account_no"),
                    rs.getInt("post_no")
            );
            return postLog;
        };
    }


    public void writePostActivityLog(Post post, String action) {
        String sql = "insert into post_log (post_log_no, logging_date, action_type, account_no, post_no) " +
                "values (null, now(), :actionType, :accountNo, :postNo)";

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("actionType", action)
                .addValue("accountNo", post.getAccountNo())
                .addValue("postNo", post.getPostNo());

        jdbcTemplate.update(sql, param);
    }
}
