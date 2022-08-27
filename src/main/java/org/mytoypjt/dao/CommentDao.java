package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.DBUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDao {

    public CommentDao() {
        super();
    }

    public List<Comment> getComments(int postNo) {
        String sql = "select " +
                "comment_no, content, reply_count, account_no, post_no, is_use_anonymous_name, nicname, commented_date" +
                " from comment where post_no = ?";
        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Comment> comments = new ArrayList<Comment>();
            while (resultSet.next()) {
                comments.add(new Comment(
                                resultSet.getInt("comment_no"),
                                resultSet.getString("content"),
                                resultSet.getInt("reply_count"),
                                resultSet.getInt("account_no"),
                                resultSet.getInt("post_no"),
                                resultSet.getBoolean("is_use_anonymous_name"),
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
                "(comment_no, content, reply_count, account_no, post_no, is_use_anonymous_name, nicname, commented_date) " +
                "values (null , ?, 0, ?, ?, ?, ?, now())";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            String nicname = profile.getNicname();
            if (isAnonymous) nicname = "누군가";

            preparedStatement.setString(1, content);
            preparedStatement.setInt(2, profile.getAccountNo());
            preparedStatement.setInt(3, postNo);
            preparedStatement.setBoolean(4, isAnonymous);
            preparedStatement.setString(5, nicname);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCommentCount(int postNo) {
        String sql = "select count(*) from comment where post_no=?";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, count);
            preparedStatement.setInt(2, postNo);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Comment getCommentByCommentNo(int commentNo) {
        String sql = "select * from comment where comment_no = ?";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, commentNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Comment(
                        resultSet.getInt("comment_no"),
                        resultSet.getString("content"),
                        resultSet.getInt("reply_count"),
                        resultSet.getInt("account_no"),
                        resultSet.getInt("post_no"),
                        resultSet.getBoolean("is_use_anonymous_name"),
                        resultSet.getString("nicname"),
                        resultSet.getDate("commented_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Comment> getCommentsByAccountNo(int accountNo) {
        String sql = "select * from comment where account_no = ?";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Comment> comments = getComments(resultSet);
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Comment> getComments(ResultSet resultSet) {
        List<Comment> comments = new ArrayList<Comment>();
        try {
            while (resultSet.next()) {
                comments.add(new Comment(
                                resultSet.getInt("comment_no"),
                                resultSet.getString("content"),
                                resultSet.getInt("reply_count"),
                                resultSet.getInt("account_no"),
                                resultSet.getInt("post_no"),
                                resultSet.getBoolean("is_use_anonymous_name"),
                                resultSet.getString("nicname"),
                                resultSet.getDate("commented_date")
                        )
                );
            }

            return comments;
        } catch(Exception e) {
            return null;
        }
    }
}
