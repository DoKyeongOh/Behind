package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostsOptionVO;

import java.util.List;
import java.util.Map;

public class RealTimeStrategy extends BasePostsStrategy{
    public RealTimeStrategy(int postCountInPage) {
        super(postCountInPage);
    }

    @Override
    public List<Post> getPosts(PostsOptionVO optionVO, Map<String, String[]> paramMap) {
        try {
            PostDao postDao = new PostDao();
            int pageNo = Integer.parseInt(optionVO.getPageNo());
            return postDao.getRealTimePosts(pageNo, this.postCountInPage);
        } catch(Exception e) {
            return null;
        }
    }
}
