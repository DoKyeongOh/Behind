package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostsOptionVO;

import java.util.List;
import java.util.Map;

public abstract class BasePostsStrategy {

    int postCountInPage = 12;

    protected PostDao postDao;

    public BasePostsStrategy(int postCountInPage) {
        this.postCountInPage = postCountInPage;
        postDao = new PostDao();
    }

    public abstract List<Post> getPosts(PostsOptionVO optionVO, Map<String, String[]> paramMap);

}
