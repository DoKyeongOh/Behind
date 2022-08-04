package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Profile;
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

            List<Reply> replies = getReplies(resultSet);
            return replies;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createReply(String content, Profile profile, int commentNoInt, boolean isAnonName) {
        String sql = "insert into reply " +
                "(reply_no, content, is_use_anonymous_name, account_no, comment_no, nicname, replied_date)" +
                "values " +
                "(null, ?, ?, ?, ?, ?, now())";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, content);
            preparedStatement.setBoolean(2, isAnonName);
            preparedStatement.setInt(3, profile.getAccountNo());
            preparedStatement.setInt(4, commentNoInt);
            preparedStatement.setString(5, profile.getNicname());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reply> getRepliesByAccountNo(int accountNo) {
        String sql = "select * from reply where account_no = ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Reply> replies = getReplies(resultSet);
            return replies;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    List<Reply> getReplies(ResultSet resultSet) {

        List<Reply> replies = new ArrayList<Reply>();
        try {
            while (resultSet.next()) {
                Reply reply = new Reply(
                        resultSet.getInt("reply_no"),
                        resultSet.getString("content"),
                        resultSet.getBoolean("is_use_anonymous_name"),
                        resultSet.getInt("account_no"),
                        resultSet.getInt("comment_no"),
                        resultSet.getString("nicname"),
                        resultSet.getDate("replied_date")
                );

                if (!Reply.isCorrectReply(reply))
                    continue;

                replies.add(reply);
            }
            return replies;
        } catch(Exception e) {
            return null;
        }
    }
}
