package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RealTimePostsStrategy extends BasePostsStrategy {

    @Autowired
    PostDao postDao;

    public RealTimePostsStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.REAL_TIME;
    }

    @Override
    public List<Post> getPosts(PostOption optionVO) {
        int pageNo = Integer.parseInt(optionVO.getPageNo());
        return postDao.getRealTimePosts(pageNo, optionVO.getPostCountLimitInPage());
    }
}
