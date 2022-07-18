package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.etc.PostSortType;
import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    int pictureCountInPage = 12;

    public List<Post> getPosts(PostSortType sortType, int pageNo){
        int startNo = (pageNo - 1) * pictureCountInPage;
        if (startNo < 0) startNo = 1;
        String sql = "";

        switch (sortType) {
            case REAL_TIME: {
                sql = "select * from post order by posted_date desc limit ?, ?";
                break;
            }
            case DAYS_FAVORITE: {
                sql = "select * from post " +
                        "where posted_date between date_add(now(), interval -1 day) and now() " +
                        "order by like_count desc limit ?, ?";
                break;
            }
            case WEEKS_FAVORITE: {
                sql = "select * from post " +
                        "where posted_date between date_add(now(), interval -1 week) and now() " +
                        "order by like_count desc limit ?, ?";
                break;
            }
            default:
                sql = "select * from post order by posted_date desc limit ?, ?";
        }

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
