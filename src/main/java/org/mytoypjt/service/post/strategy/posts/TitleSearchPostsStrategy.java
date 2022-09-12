package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TitleSearchPostsStrategy extends BasePostsStrategy {

    @Autowired
    PostDao postDao;

    public TitleSearchPostsStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.SEARCH_TITLE_FROM_USER;
    }

    @Override
    public List<Post> getPosts(PostOption optionVO) {
        return null;
    }
}
