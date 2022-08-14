package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.mytoypjt.service.annotation.Transaction;

import java.util.List;
import java.util.Map;

public class ContentSearchStrategy extends BasePostsStrategy {

    public ContentSearchStrategy() {
    }

    @Override
    public List<Post> getPosts(PostsOptionVO optionVO, Map<String, String[]> paramMap) {
        return null;
    }
}
