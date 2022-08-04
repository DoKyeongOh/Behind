package org.mytoypjt.dao;

import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PostLogDao {


    public void writeCreationLog(int accountNo, int postNo) {
        String sql = "insert into post_log (post_log_no, logging_date, action_type, account_no) " +
                "values (null, now(), ?, ?)";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ) {
            preparedStatement.setString(1, "게시");
            preparedStatement.setInt(2, accountNo);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
