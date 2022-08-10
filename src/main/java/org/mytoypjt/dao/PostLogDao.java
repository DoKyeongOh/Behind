package org.mytoypjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PostLogDao extends BaseTransactionDao {

    public PostLogDao(Connection connection) {
        super(connection);
    }

    public void writePostActivityLog(int accountNo, int postNo, String action) {
        String sql = "insert into post_log (post_log_no, logging_date, action_type, account_no, post_no) " +
                "values (null, now(), ?, ?, ?)";

        Connection conn = connection;
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){

            preparedStatement.setString(1, action);
            preparedStatement.setInt(2, accountNo);
            preparedStatement.setInt(3, postNo);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
