package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {

    public List<Comment> getComments(int postNo) {
        String sql = "select " +
                "comment_no, content, reply_count, is_use_anonymous_name, account_no, post_no, nicname, commented_date" +
                " from comment where post_no = ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Comment> comments = new ArrayList<Comment>();
            while (resultSet.next()) {
                comments.add(new Comment(
                                resultSet.getInt("comment_no"),
                                resultSet.getString("content"),
                                resultSet.getInt("reply_count"),
                                resultSet.getBoolean("is_use_anonymous_name"),
                                resultSet.getInt("account_no"),
                                resultSet.getInt("post_no"),
                                resultSet.getString("nicname"),
                                resultSet.getDate("commented_date")
                        )
                );
            }
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createComment(int postNo, Profile profile, boolean isAnonymous, String content) {
        String sql = "insert into comment " +
                "(comment_no, content, reply_count,is_use_anonymous_name, account_no, post_no, nicname, commented_date) " +
                "values (null , ?, 0, ?, ?, ?, ?, now())";

        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, content);
            preparedStatement.setBoolean(2, isAnonymous);
            preparedStatement.setInt(3, profile.getAccountNo());
            preparedStatement.setInt(4, postNo);
            preparedStatement.setString(5, profile.getNicname());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public Comment getComment(int commentNo) {
        String sql = "select * from comment where comment_no = ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, commentNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Comment(
                        resultSet.getInt("comment_no"),
                        resultSet.getString("content"),
                        resultSet.getInt("reply_count"),
                        resultSet.getBoolean("is_use_anonymous_name"),
                        resultSet.getInt("account_no"),
                        resultSet.getInt("post_no"),
                        resultSet.getString("nicname"),
                        resultSet.getDate("commented_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
