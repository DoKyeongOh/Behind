package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Comment;
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
                                resultSet.getInt("comment_id"),
                                resultSet.getString("content"),
                                resultSet.getInt("reply_count"),
                                resultSet.getBoolean("is_use_anonymous_name"),
                                resultSet.getInt("account_no"),
                                resultSet.getInt("post_id")
                        )
                );
            }
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
