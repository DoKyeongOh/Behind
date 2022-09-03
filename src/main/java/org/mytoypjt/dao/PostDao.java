package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Like;
import org.mytoypjt.models.entity.Post;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PostDao {

    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsert;
    RowMapper<Post> postRowMapper;

    public PostDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("post");
        postRowMapper = (rs, rowNum) -> {
            Post post = new Post(
                    rs.getInt("post_no"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getDate("posted_date"),
                    rs.getInt("comment_count"),
                    rs.getInt("like_count"),
                    rs.getInt("account_no"),
                    rs.getInt("picture_no"),
                    rs.getBoolean("name_anonymous"),
                    rs.getBoolean("city_anonymous"),
                    rs.getString("nicname"),
                    rs.getString("city"));
            return post;
        };
    }

    public List<Post> getRealTimePosts(int pageNo, int postCountInPage) {
        int startNo = (pageNo - 1) * postCountInPage;
        if (startNo < 0) startNo = 1;
        String sql = "select * from post order by posted_date desc limit :startNo, :count";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("startNo", startNo);
        param.addValue("count", postCountInPage);

        return jdbcTemplate.query(sql, param, postRowMapper);
    }

    public List<Post> getDaysFavoritePosts(int pageNo, int postCountInPage) {
        int startNo = (pageNo - 1) * postCountInPage;
        if (startNo < 0) startNo = 1;
        String sql = "select * from post " + "where posted_date between date_add(now(), interval -1 day) and now() " + "order by like_count desc limit ?, ?";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("startNo", startNo);
        param.addValue("count", postCountInPage);

        return jdbcTemplate.query(sql, param, postRowMapper);
    }

    public List<Post> getWeeksFavoritePosts(int pageNo, int postCountInPage) {
        int startNo = (pageNo - 1) * postCountInPage;
        if (startNo < 0) startNo = 1;
        String sql = "select * from post " + "where posted_date between date_add(now(), interval -1 week) and now() " + "order by like_count desc limit ?, ?";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("startNo", startNo);
        param.addValue("count", postCountInPage);

        return jdbcTemplate.query(sql, param, postRowMapper);
    }

    public Post getPost(int postNo) {
        String sql = "select * from post where post_no = :postNo";
        MapSqlParameterSource param = new MapSqlParameterSource("postNo", postNo);
        List<Post> posts = jdbcTemplate.query(sql, param, postRowMapper);
        return posts.size() == 0 ? null : posts.get(0);
    }

    public void updateLikeCount(int postNo, int count) {
        String sql = "update post set like_count = :count where post_no = :postNo";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("count", count);
        param.addValue("postNo", postNo);

        jdbcTemplate.update(sql, param);
    }

    public int getTotalPostCount() {
        String sql = "select count(*) from post";
        int count = jdbcTemplate.queryForObject(sql, (SqlParameterSource) null, (rs, rowNum) -> {
            return rs.getInt(1);
        });

        return count;
    }

    public int getDaysPostCount(int postCountInPage) {
        String sql = "select count(*) from post " + "where posted_date between date_add(now(), interval -1 day) and now() " + "order by like_count desc limit 0, ?";

        int count = jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("count", postCountInPage), (rs, rowNum) -> rs.getInt(1));

        return count;
    }

    public int getWeeksPostCount(int postCountInPage) {
        String sql = "select count(*) from post " + "where posted_date between date_add(now(), interval -1 week) and now() " + "order by like_count desc limit 0, ?";

        int count = jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("count", postCountInPage), (rs, rowNum) -> rs.getInt(1));

        return count;
    }

    public boolean createPost(Post post) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(post);
        int returnCode = jdbcInsert.execute(param);
        if (returnCode == 1) return true;
        return false;
    }

    public void updatePost(Post post) {
        String sql = "update post set title=:title, content=:content, picture_no=:pictureNo, " +
                "name_anonymous=:nameAnonymous, city_anonymous=:cityAnonymous, " +
                "nicname=:nicname, city=:city where post_no=:postNo";

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(post);
        jdbcTemplate.update(sql, param);
    }

    public List<Post> getPostsByAccountNo(int accountNo) {
        String sql = "select * from post where account_no = :accountNo";

        MapSqlParameterSource param = new MapSqlParameterSource("accountNo", accountNo);

        return jdbcTemplate.query(sql, param, postRowMapper);
    }
}
