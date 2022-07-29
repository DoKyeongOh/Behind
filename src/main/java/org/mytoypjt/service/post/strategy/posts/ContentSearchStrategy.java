package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostsOptionVO;

import java.util.List;
import java.util.Map;

public class ContentSearchStrategy extends BasePostsStrategy{

    public ContentSearchStrategy(int postCountInPage) {
        super(postCountInPage);
    }

    @Override
    public List<Post> getPosts(PostsOptionVO optionVO, Map<String, String[]> paramMap) {
        return null;
    }
}
