package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    public List<Post> getRealTimePosts(int pageNo, int postCountInPage){
        int startNo = (pageNo - 1) * postCountInPage;
        if (startNo < 0) startNo = 1;
        String sql = "select * from post order by posted_date desc limit ?, ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, startNo);
            preparedStatement.setInt(2, postCountInPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> postList = getPosts(resultSet);
            return postList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Post> getDaysFavoritePosts(int pageNo, int postCountInPage){
        int startNo = (pageNo - 1) * postCountInPage;
        if (startNo < 0) startNo = 1;
        String sql = "select * from post " +
                "where posted_date between date_add(now(), interval -1 day) and now() " +
                "order by like_count desc limit ?, ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, startNo);
            preparedStatement.setInt(2, postCountInPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> postList = getPosts(resultSet);
            return postList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Post> getWeeksFavoritePosts(int pageNo, int postCountInPage){
        int startNo = (pageNo - 1) * postCountInPage;
        if (startNo < 0) startNo = 1;
        String sql = "select * from post " +
                "where posted_date between date_add(now(), interval -1 week) and now() " +
                "order by like_count desc limit ?, ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, startNo);
            preparedStatement.setInt(2, postCountInPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> postList = getPosts(resultSet);
            return postList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    List<Post> getPosts(ResultSet resultSet){
        List<Post> postList = new ArrayList<Post>();
        try {
            while (resultSet.next()) {
                postList.add(new Post(
                        resultSet.getInt("post_no"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("posted_date"),
                        resultSet.getBoolean("is_use_anonymous_city"),
                        resultSet.getBoolean("is_use_anonymous_name"),
                        resultSet.getInt("comment_count"),
                        resultSet.getInt("like_count"),
                        resultSet.getInt("account_no"),
                        resultSet.getInt("picture_no"),
                        resultSet.getString("nicname")
                        )
                );
            }

            return postList;
        } catch(Exception e) {
            return null;
        }
    }

    public List<Post> getPosts(PostSortType sortType, int pageNo, int postCountInPage){
        int startNo = (pageNo - 1) * postCountInPage;
        if (startNo < 0) startNo = 1;
        String sql = "";

        switch (sortType) {
            case REAL_TIME: {
                sql = "select * from post order by posted_date desc limit ?, ?";
                break;
            }
            case DAYS_FAVORITE: {
                sql = "select * from post " +
                        "where posted_date between date_add(now(), interval -1 day) and now() " +
                        "order by like_count desc limit ?, ?";
                break;
            }
            case WEEKS_FAVORITE: {
                sql = "select * from post " +
                        "where posted_date between date_add(now(), interval -1 week) and now() " +
                        "order by like_count desc limit ?, ?";
                break;
            }
            default:
                sql = "select * from post order by posted_date desc limit ?, ?";
        }

        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, startNo);
            preparedStatement.setInt(2, postCountInPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> postList = getPosts(resultSet);
            return postList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Post getPost(int postNo) {
        String sql = "select * from post where post_no = ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            Post post = null;

            if (resultSet.next()) {
                post = new Post(
                        resultSet.getInt("post_no"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("posted_date"),
                        resultSet.getBoolean("is_use_anonymous_city"),
                        resultSet.getBoolean("is_use_anonymous_name"),
                        resultSet.getInt("comment_count"),
                        resultSet.getInt("like_count"),
                        resultSet.getInt("account_no"),
                        resultSet.getInt("picture_no"),
                        resultSet.getString("nicname")
                );
            }

            return post;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isAlreadyLikeThis(int postNo, int accountNo) {
        String sql = "select * from likes where post_no = ? and account_no = ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postNo);
            preparedStatement.setInt(2, accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addLike(int postNo, int accountNo) {
        modifyLike(postNo, accountNo, true);
    }

    public void delLike(int postNo, int accountNo) {
        modifyLike(postNo, accountNo, false);
    }

    public void modifyLike(int postNo, int accountNo, boolean isAdd){
        String sql = "delete from likes where post_no=? and account_no=?";
        if (isAdd)
            sql = "insert into likes (like_no, post_no, account_no) values (null, ?, ?)";

        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postNo);
            preparedStatement.setInt(2, accountNo);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLikeCount(int postNo) {
        String sql = "select count(*) from likes where post_no=?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateLikeCount(int postNo, int count){
        String sql = "update post set like_count = ? where post_no = ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, count);
            preparedStatement.setInt(2, postNo);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserLikePost(int postNo, int accountNo){
        String sql = "select * from likes where post_no=? and account_no=?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postNo);
            preparedStatement.setInt(2, accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getPostCount() {
        String sql = "select count(*) from post";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getDaysPostCount(int postCountInPage) {
        String sql = "select count(*) from post " +
                "where posted_date between date_add(now(), interval -1 day) and now() " +
                "order by like_count desc limit 0, ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postCountInPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getWeeksPostCount(int postCountInPage) {
        String sql = "select count(*) from post " +
                "where posted_date between date_add(now(), interval -1 week) and now() " +
                "order by like_count desc limit 0, ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postCountInPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void createPost(Profile profile, String title, String content, boolean isAnonymousName, boolean isAnonymousCity, int imgNo) {
        String sql = "insert into post " +
                "(post_no, title, content, posted_date, is_use_anonymous_city, is_use_anonymous_name, " +
                "comment_count, like_count, account_no, picture_no, nicname) " +
                "values " +
                "(null , ?, ?, now(), ?, ?, 0, 0, ?, ?, ?)";

        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            preparedStatement.setBoolean(3, isAnonymousName);
            preparedStatement.setBoolean(4, isAnonymousCity);
            preparedStatement.setInt(5, profile.getAccountNo());
            preparedStatement.setInt(6, imgNo);
            preparedStatement.setString(7, profile.getNicname());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Post> getPostsByAccountNo(int accountNo) {
        String sql = "select * from post where account_no = ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> postList = getPosts(resultSet);
            return postList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Post getLastPost(int accountNo) {
        String sql = "select * from post order by posted_date desc limit 1";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Post post = null;

            if (resultSet.next()) {
                return new Post(
                        resultSet.getInt("post_no"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("posted_date"),
                        resultSet.getBoolean("is_use_anonymous_city"),
                        resultSet.getBoolean("is_use_anonymous_name"),
                        resultSet.getInt("comment_count"),
                        resultSet.getInt("like_count"),
                        resultSet.getInt("account_no"),
                        resultSet.getInt("picture_no"),
                        resultSet.getString("nicname")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
