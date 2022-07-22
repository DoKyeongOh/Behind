package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {

    public List<Comment> getComments(int postNo) {
        String sql = "select * from comment where post_no = ?";
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
                                resultSet.getString("nicname")
                        )
                );
            }
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createComment(int postNo, Profile profile, boolean isAnonymous, String content) {
        String sql = "insert into comment values (null , ?, 0, ?, ?, ?, ?)";

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
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
