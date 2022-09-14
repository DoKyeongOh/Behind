package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Post;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

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
        String sql = "select * from post " +
                "where posted_date between date_add(now(), interval -1 day) and now() " +
                "order by like_count desc limit :startNo, :count";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("startNo", startNo);
        param.addValue("count", postCountInPage);

        return jdbcTemplate.query(sql, param, postRowMapper);
    }

    public List<Post> getWeeksFavoritePosts(int pageNo, int postCountInPage) {
        int startNo = (pageNo - 1) * postCountInPage;
        if (startNo < 0) startNo = 1;
        String sql = "select * from post " +
                "where posted_date between date_add(now(), interval -1 week) and now() " +
                "order by like_count desc limit :startNo, :count";

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
        return jdbcTemplate.queryForObject(sql, (SqlParameterSource) null, (rs, rowNum) -> {
            return rs.getInt(1);
        });
    }

    public int getDaysPostCount() {
        String sql = "select count(*) from post " +
                "where posted_date between date_add(now(), interval -1 day) and now() ";

        return jdbcTemplate.queryForObject(sql, (SqlParameterSource) null, (rs, rowNum) -> rs.getInt(1));
    }

    public int getWeeksPostCount() {
        String sql = "select count(*) from post " +
                "where posted_date between date_add(now(), interval -1 week) and now() ";

        return jdbcTemplate.queryForObject(sql, (SqlParameterSource) null, (rs, rowNum) -> rs.getInt(1));
    }

    public boolean createPost(Post post) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(post);
        return jdbcInsert.execute(param) == 1;
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

    public List<Integer> getPostNoListOfAllPost() {
        String sql = "SELECT * FROM POST";
        List<Integer> postNoList = new ArrayList<>();

        jdbcTemplate
                .query(sql, (SqlParameterSource) null, postRowMapper)
                .forEach(post -> {
                    postNoList.add(post.getPostNo());
                });
        return postNoList;
    }

    public void saveCommentCount(Integer postNo, int commentCount) {
        String sql = "UPDATE post SET comment_count=:commentCount WHERE post_no=:postNo";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("commentCount", commentCount);
        param.addValue("postNo", postNo);
        jdbcTemplate.update(sql, param);
    }

    public void updateCommentCount(int postNo, int count){
        String sql = "update post set comment_count = :commentCount where post_no = :postNo";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("commentCount", count);
        param.addValue("postNo", postNo);
        jdbcTemplate.update(sql, param);
    }

    public void deletePost(int postNo) {
        String sql = "delete from post where post_no = :postNo";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("postNo", postNo);
        jdbcTemplate.update(sql, param);
    }
}
