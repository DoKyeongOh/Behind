package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Post;
import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    int pictureCountInPage = 12;

    public List<Post> getPosts(int pageNo){
        int startNo = (pageNo - 1) * pictureCountInPage;
        if (startNo < 0) startNo = 1;

        String sql = "select * from post order by posted_date desc limit ?, ?";
        try (
                Connection conn = new DBUtil().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, startNo);
            preparedStatement.setInt(2, pictureCountInPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> postList = new ArrayList<Post>();

            Post post = null;
            while (resultSet.next()) {
                postList.add(new Post(
                        resultSet.getInt("post_no"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("posted_date"),
                        resultSet.getBoolean("is_use_anonymous_city"),
                        resultSet.getBoolean("is_use_anonymous_name"),
                        resultSet.getInt("comment_count"),
                        resultSet.getInt("like_count"),
                        resultSet.getInt("account_no"),
                        resultSet.getInt("picture_no")
                        )
                );
            }
            return postList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
