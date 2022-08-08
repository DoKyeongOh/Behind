package org.mytoypjt.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PostLogDao {

    private static PostLogDao postLogDao;
    private static Connection connection;

    public static synchronized PostLogDao getInstance(){
        if (postLogDao == null)
            postLogDao = new PostLogDao();

        return postLogDao;
    }

    public static synchronized void setConnection(Connection conn){
        connection = conn;
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
