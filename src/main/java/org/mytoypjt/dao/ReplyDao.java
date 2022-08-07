package org.mytoypjt.dao;

import org.apache.commons.dbcp2.BasicDataSource;
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
                
                Connection conn = DBUtil.getBasicDataSource().getConnection();
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
                "(reply_no, content, account_no, comment_no, is_use_anonymous_name, nicname, replied_date)" +
                "values " +
                "(null, ?, ?, ?, ?, ?, now())";
        try (
                Connection conn = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            String nicname = profile.getNicname();
            if (isAnonName) nicname = "누군가";

            preparedStatement.setString(1, content);
            preparedStatement.setInt(2, profile.getAccountNo());
            preparedStatement.setInt(3, commentNoInt);
            preparedStatement.setBoolean(4,isAnonName);
            preparedStatement.setString(5, nicname);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reply> getRepliesByAccountNo(int accountNo) {
        String sql = "select * from reply where account_no = ?";
        try (
                
Connection conn = DBUtil.getBasicDataSource().getConnection();
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
                        resultSet.getInt("account_no"),
                        resultSet.getInt("comment_no"),
                        resultSet.getBoolean("is_use_anonymous_name"),
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
