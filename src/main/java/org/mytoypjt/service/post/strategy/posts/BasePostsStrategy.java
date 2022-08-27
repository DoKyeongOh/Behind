package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

@Component
public abstract class BasePostsStrategy {

    int postCountInPage = 12;

    public BasePostsStrategy() {
    }

    public int getPostCountInPage() {
        return postCountInPage;
    }

    public void setPostCountInPage(int postCountInPage) {
        this.postCountInPage = postCountInPage;
    }

    public abstract List<Post> getPosts(PostsOptionVO optionVO, Map<String, String> paramMap);

}
