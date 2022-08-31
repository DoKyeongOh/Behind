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
public class ContentSearchPostsStrategy extends BasePostsStrategy {

    @Autowired
    PostDao postDao;

    public ContentSearchPostsStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.SEARCH_CONTENT_FROM_USER;
    }

    @Override
    public List<Post> getPosts(PostsOptionVO optionVO, Map<String, String> paramMap) {
        return null;
    }
}