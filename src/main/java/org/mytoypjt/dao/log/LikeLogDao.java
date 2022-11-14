package org.mytoypjt.dao.log;

import org.mytoypjt.models.entity.Like;
import org.mytoypjt.models.entity.LikeLog;
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
public class LikeLogDao {
    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;
    RowMapper<LikeLog> rowMapper;

    public LikeLogDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("likes");
        rowMapper = (rs, rowNum) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
            LikeLog likeLog = new LikeLog(
                    rs.getInt("like_log_no"),
                    sdf.format(rs.getTimestamp("logging_date")),
                    rs.getString("action_type"),
                    rs.getInt("account_no"),
                    rs.getInt("entity_no")
            );
            return likeLog;
        };
    }


    public void writeLog(Like like, String action) {
        String sql = "insert into like_log (like_log_no, logging_date, action_type, account_no, entity_no) " +
                "values (null, now(), :actionType, :accountNo, :entityNo)";

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("actionType", action)
                .addValue("accountNo", like.getAccountNo())
                .addValue("entityNo", like.getPostNo());

        jdbcTemplate.update(sql, param);
    }

    public List<LikeLog> getLogsByAccountNo(int accountNo, int count){
        String sql = "select * from like_log where account_no=:accountNo order by logging_date desc limit :count";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("accountNo", accountNo)
                .addValue("count", count);

        return jdbcTemplate.query(sql, param, rowMapper);
    }
}
