package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.etc.PostSortType;
import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    int pictureCountInPage = 12;

    public List<Post> getPosts(PostSortType sortType, int pageNo){
        int startNo = (pageNo - 1) * pictureCountInPage;
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
            preparedStatement.setInt(2, pictureCountInPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> postList = new ArrayList<Post>();

            Post post = null;
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
            sql = "insert into likes (post_no, account_no) values (?, ?)";

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

    public int getCommentCount(int postNo) {
        String sql = "select count(*) from comment where post_no=?";
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

    public void updateCommentCount(int postNo, int count){
        String sql = "update post set comment_count = ? where post_no = ?";
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

}
