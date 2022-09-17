package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Reply;
import org.mytoypjt.models.entity.ReplyLog;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class ReplyLogDao {
    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;
    RowMapper<ReplyLog> rowMapper;

    public ReplyLogDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("reply_log");
        rowMapper = (rs, rowNum) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("YY.MM.DD hh:mm:ss");
            ReplyLog replyLog = new ReplyLog(
                    rs.getInt("reply_log_no"),
                    sdf.format(rs.getTimestamp("logging_date")),
                    rs.getString("action_type"),
                    rs.getInt("account_no"),
                    rs.getInt("entity_no")
            );
            return replyLog;
        };
    }

    public void writeLog(Reply reply, String action) {
        String sql = "insert into reply_log (reply_log_no, logging_date, action_type, account_no, entity_no) " +
                "values (null, now(), :actionType, :accountNo, :entityNo)";

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("actionType", action)
                .addValue("accountNo", reply.getAccountNo())
                .addValue("entityNo", reply.getReplyNo());

        jdbcTemplate.update(sql, param);
    }

    public List<ReplyLog> getLogsByAccountNo(int accountNo, int count){
        String sql = "select * from reply_log where account_no=:accountNo order by logging_date desc limit :count";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("accountNo", accountNo)
                .addValue("count", count);

        return jdbcTemplate.query(sql, param, rowMapper);
    }
}
