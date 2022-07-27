package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Reply;
import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReplyDao {
    public List<Reply> getReplies(int commentNo) {
        String sql = "select * from reply where comment_no = ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, commentNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Reply> replies = new ArrayList<Reply>();
            while (resultSet.next()) {
                Reply reply = new Reply(
                        resultSet.getInt("reply_no"),
                        resultSet.getString("content"),
                        resultSet.getBoolean("is_use_anonymous_name"),
                        resultSet.getInt("account_no"),
                        resultSet.getInt("comment_no"),
                        resultSet.getString("nicname")
                );

                if (!Reply.isCorrectReply(reply))
                    continue;

                replies.add(reply);
            }
            return replies;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
