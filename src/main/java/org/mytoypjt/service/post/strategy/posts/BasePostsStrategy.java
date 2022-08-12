package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostsOptionVO;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public abstract class BasePostsStrategy {

    int postCountInPage = 12;

    protected Connection connection;

    public BasePostsStrategy() {
    }

    public int getPostCountInPage() {
        return postCountInPage;
    }

    public void setPostCountInPage(int postCountInPage) {
        this.postCountInPage = postCountInPage;
    }

    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public abstract List<Post> getPosts(PostsOptionVO optionVO, Map<String, String[]> paramMap);

}
