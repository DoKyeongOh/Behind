package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Like;
import org.mytoypjt.utils.DBUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LikeDao {

    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;
    RowMapper<Like> likeRowMapper;

    public LikeDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("post");
        likeRowMapper = (rs, rowNum) -> {
            Like like = new Like(
                    rs.getInt("like_no"),
                    rs.getInt("account_no"),
                    rs.getInt("post_no")
            );
            return like;
        };
    }
    public boolean isAlreadyLikeThis(int postNo, int accountNo) {
        String sql = "select * from likes where post_no = :postNo and account_no = :accountNo";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("postNo", postNo);
        param.addValue("accountNo", accountNo);

        List<Like> likes = jdbcTemplate.query(sql, param, likeRowMapper);
        if (likes.size() > 0)
            return true;
        return false;
    }
    public void addLike(int postNo, int accountNo) {
        String sql = "insert into likes (like_no, post_no, account_no) values (null, :postNo, :accountNo)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("postNo", postNo);
        param.addValue("accountNo", accountNo);

        jdbcTemplate.update(sql, param);
    }
    public void delLike(int postNo, int accountNo) {
        String sql = "delete from likes where post_no=:postNo and account_no=:accountNo";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("postNo", postNo);
        param.addValue("accountNo", accountNo);

        jdbcTemplate.update(sql, param);
    }
    public int getLikeCount(int postNo) {
        String sql = "select count(*) from likes where post_no=:postNo";

        Map<String, Integer> param = new HashMap<>();
        param.put("postNo", postNo);

        int count = jdbcTemplate.queryForObject(sql, param, (rs, rowNum) -> {
            return rs.getInt(1);
        });

        return count;
    }
    public boolean isUserLikePost(int postNo, int accountNo){
        String sql = "select * from likes where post_no=:postNo and account_no=:accountNo";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("postNo", postNo);
        param.addValue("accountNo", accountNo);

        boolean likeExist = jdbcTemplate.query(sql, param, likeRowMapper).size() != 0;
        return likeExist;
    }
}
