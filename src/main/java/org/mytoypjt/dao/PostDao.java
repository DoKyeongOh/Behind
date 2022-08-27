package org.mytoypjt.dao;

import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.DBUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDao {

    public PostDao() {
    }

    public List<Post> getRealTimePosts(int pageNo, int postCountInPage){
        int startNo = (pageNo - 1) * postCountInPage;
        if (startNo < 0) startNo = 1;
        String sql = "select * from post order by posted_date desc limit ?, ?";
        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, startNo);
            preparedStatement.setInt(2, postCountInPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> postList = getPosts(resultSet);
            return postList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Post> getDaysFavoritePosts(int pageNo, int postCountInPage){
        int startNo = (pageNo - 1) * postCountInPage;
        if (startNo < 0) startNo = 1;
        String sql = "select * from post " +
                "where posted_date between date_add(now(), interval -1 day) and now() " +
                "order by like_count desc limit ?, ?";
        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, startNo);
            preparedStatement.setInt(2, postCountInPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> postList = getPosts(resultSet);
            return postList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Post> getWeeksFavoritePosts(int pageNo, int postCountInPage){
        int startNo = (pageNo - 1) * postCountInPage;
        if (startNo < 0) startNo = 1;
        String sql = "select * from post " +
                "where posted_date between date_add(now(), interval -1 week) and now() " +
                "order by like_count desc limit ?, ?";
        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, startNo);
            preparedStatement.setInt(2, postCountInPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> postList = getPosts(resultSet);
            return postList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    List<Post> getPosts(ResultSet resultSet){
        List<Post> postList = new ArrayList<Post>();
        try {
            while (resultSet.next()) {
                postList.add(new Post(
                        resultSet.getInt("post_no"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("posted_date"),
                        resultSet.getInt("comment_count"),
                        resultSet.getInt("like_count"),
                        resultSet.getInt("account_no"),
                        resultSet.getInt("picture_no"),
                        resultSet.getBoolean("is_use_anonymous_name"),
                        resultSet.getBoolean("is_use_anonymous_city"),
                        resultSet.getString("nicname"),
                        resultSet.getString("city")
                        )
                );
            }

            return postList;
        } catch(Exception e) {
            return null;
        }
    }

    public Post getPost(int postNo) {
        String sql = "select * from post where post_no = ?";
        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            Post post = null;

            if (resultSet.next()) {
                post = new Post(
                        resultSet.getInt("post_no"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("posted_date"),
                        resultSet.getInt("comment_count"),
                        resultSet.getInt("like_count"),
                        resultSet.getInt("account_no"),
                        resultSet.getInt("picture_no"),
                        resultSet.getBoolean("is_use_anonymous_name"),
                        resultSet.getBoolean("is_use_anonymous_city"),
                        resultSet.getString("nicname"),
                        resultSet.getString("city")
                );
            }

            return post;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isAlreadyLikeThis(int postNo, int accountNo) {
        String sql = "select * from likes where post_no = ? and account_no = ?";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postNo);
            preparedStatement.setInt(2, accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addLike(int postNo, int accountNo) {
        modifyLike(postNo, accountNo, true);
    }

    public void delLike(int postNo, int accountNo) {
        modifyLike(postNo, accountNo, false);
    }

    public void modifyLike(int postNo, int accountNo, boolean isAdd){
        String sql = "delete from likes where post_no=? and account_no=?";
        if (isAdd)
            sql = "insert into likes (like_no, post_no, account_no) values (null, ?, ?)";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postNo);
            preparedStatement.setInt(2, accountNo);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLikeCount(int postNo) {
        String sql = "select count(*) from likes where post_no=?";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateLikeCount(int postNo, int count){
        String sql = "update post set like_count = ? where post_no = ?";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, count);
            preparedStatement.setInt(2, postNo);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserLikePost(int postNo, int accountNo){
        String sql = "select * from likes where post_no=? and account_no=?";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postNo);
            preparedStatement.setInt(2, accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getTotalPostCount() {
        String sql = "select count(*) from post";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getDaysPostCount(int postCountInPage) {
        String sql = "select count(*) from post " +
                "where posted_date between date_add(now(), interval -1 day) and now() " +
                "order by like_count desc limit 0, ?";
        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postCountInPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getWeeksPostCount(int postCountInPage) {
        String sql = "select count(*) from post " +
                "where posted_date between date_add(now(), interval -1 week) and now() " +
                "order by like_count desc limit 0, ?";
        try (
                
Connection conn = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, postCountInPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean createPost(Profile profile, Post post) {
        String sql = "insert into post " +
                "(post_no, title, content, posted_date, comment_count, like_count, account_no, picture_no, is_use_anonymous_name, is_use_anonymous_city, nicname, city) " +
                "values " +
                "(null , ?, ?, now(), 0, 0, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            String nicname = (post.getIsUseAnonymousName()) ? "누군가" : post.getNicname();
            String city = (post.getIsUseAnonymousCity()) ? "어딘가" : post.getCity();

            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setInt(3, profile.getAccountNo());
            preparedStatement.setInt(4, post.getPictureNo());
            preparedStatement.setBoolean(5, post.getIsUseAnonymousName());
            preparedStatement.setBoolean(6, post.getIsUseAnonymousCity());
            preparedStatement.setString(7, nicname);
            preparedStatement.setString(8, city);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updatePost(Post post) {
        String sql = "update post set title=?, content=?, picture_no=?, " +
                "is_use_anonymous_name=?, is_use_anonymous_city=?, nicname=?, city=?" +
                "where post_no=?";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            String nicname = (post.getIsUseAnonymousName()) ? "누군가" : post.getNicname();
            String city = (post.getIsUseAnonymousCity()) ? "어딘가" : post.getCity();

            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setInt(3, post.getPictureNo());
            preparedStatement.setBoolean(4, post.getIsUseAnonymousName());
            preparedStatement.setBoolean(5, post.getIsUseAnonymousCity());
            preparedStatement.setString(6, nicname);
            preparedStatement.setString(7, city);
            preparedStatement.setInt(8, post.getPostNo());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Post> getPostsByAccountNo(int accountNo) {
        String sql = "select * from post where account_no = ?";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> postList = getPosts(resultSet);
            return postList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Post getLastPost(int accountNo) {
        String sql = "select * from post order by posted_date desc limit 1";

        try (
                Connection connection = DBUtil.getBasicDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Post post = null;

            if (resultSet.next()) {
                return new Post(
                        resultSet.getInt("post_no"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("posted_date"),
                        resultSet.getInt("comment_count"),
                        resultSet.getInt("like_count"),
                        resultSet.getInt("account_no"),
                        resultSet.getInt("picture_no"),
                        resultSet.getBoolean("is_use_anonymous_name"),
                        resultSet.getBoolean("is_use_anonymous_city"),
                        resultSet.getString("nicname"),
                        resultSet.getString("city")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
