package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

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
    public List<Post> getPosts(PostsOptionVO optionVO, Map<String, String> paramMap) {
        try {
            int pageNo = Integer.parseInt(optionVO.getPageNo());
            return postDao.getRealTimePosts(pageNo, this.postCountInPage);
        } catch(Exception e) {
            return null;
        }
    }
}
