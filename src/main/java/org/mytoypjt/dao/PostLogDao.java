package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.PostLog;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

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
                    rs.getInt("entity_no")
            );
            return postLog;
        };
    }


    public void writeLog(Post post, String action) {
        String sql = "insert into post_log (post_log_no, logging_date, action_type, account_no, entity_no) " +
                "values (null, now(), :actionType, :accountNo, :entityNo)";

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("actionType", action)
                .addValue("accountNo", post.getAccountNo())
                .addValue("entityNo", post.getPostNo());

        jdbcTemplate.update(sql, param);
    }

    public List<PostLog> getLogsByAccountNo(int accountNo, int count){
        String sql = "select * from post_log where account_no=:accountNo order by logging_date desc limit :count";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("accountNo", accountNo)
                .addValue("count", count);

        return jdbcTemplate.query(sql, param, postLogRowMapper);
    }
}
