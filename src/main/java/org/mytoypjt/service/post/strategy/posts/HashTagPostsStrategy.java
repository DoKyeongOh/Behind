package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class HashTagPostsStrategy extends BasePostsStrategy {

    @Autowired
    PostDao postDao;

    public HashTagPostsStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.HASH_TAG;
    }

    @Override
    public List<Post> getPosts(PostsOptionVO optionVO, Map<String, String> paramMap) {
        return null;
    }
}
