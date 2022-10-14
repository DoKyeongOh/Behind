package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NicNameSearchPostsStrategy extends BasePostsStrategy {

    @Autowired
    PostDao postDao;

    public NicNameSearchPostsStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.SEARCH_BY_NICNAME;
    }

    @Override
    public List<Post> getPosts(PostOption optionVO) {
        System.out.println("dd");
        return null;
    }
}
