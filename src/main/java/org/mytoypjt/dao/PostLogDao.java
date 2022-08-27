package org.mytoypjt.dao;

import org.mytoypjt.utils.DBUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class PostLogDao {

    public PostLogDao() {
    }

    public void writePostActivityLog(int accountNo, int postNo, String action) {
        String sql = "insert into post_log (post_log_no, logging_date, action_type, account_no, post_no) " +
                "values (null, now(), ?, ?, ?)";

        try(
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){

            preparedStatement.setString(1, action);
            preparedStatement.setInt(2, accountNo);
            preparedStatement.setInt(3, postNo);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
