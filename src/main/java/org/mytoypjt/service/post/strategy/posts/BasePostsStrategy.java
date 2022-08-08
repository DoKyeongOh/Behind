package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostsOptionVO;

import java.util.List;
import java.util.Map;

public abstract class BasePostsStrategy {

    int postCountInPage = 12;

    public BasePostsStrategy(int postCountInPage) {
        this.postCountInPage = postCountInPage;
    }

    public abstract List<Post> getPosts(PostsOptionVO optionVO, Map<String, String[]> paramMap);

}
