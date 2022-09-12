package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostOption;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class BasePostsStrategy {

    public BasePostsStrategy() {
    }

    public abstract PostSortType getSortType();

    public abstract List<Post> getPosts(PostOption optionVO);

}
