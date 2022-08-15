package org.mytoypjt.dao;

import org.mytoypjt.utils.TransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PostLogDao extends BaseTransactionDao {

    public PostLogDao() {
        super();
    }

    public PostLogDao(Connection connection) {
        super(connection);
    }

    @Override
    public void setConnection(Connection connection) {
        super.setConnection(connection);
    }

    public void writePostActivityLog(int accountNo, int postNo, String action) {
        String sql = "insert into post_log (post_log_no, logging_date, action_type, account_no, post_no) " +
                "values (null, now(), ?, ?, ?)";

        Connection conn = this.connection;
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
