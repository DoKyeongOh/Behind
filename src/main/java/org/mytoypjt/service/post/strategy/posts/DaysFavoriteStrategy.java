package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.mytoypjt.service.annotation.Transaction;

import java.util.List;
import java.util.Map;

public class DaysFavoriteStrategy extends BasePostsStrategy {
    public DaysFavoriteStrategy() {

    }

    @Override
    public List<Post> getPosts(PostsOptionVO optionVO, Map<String, String[]> paramMap) {
        try {
            PostDao postDao = new PostDao();
            int pageNo = Integer.parseInt(optionVO.getPageNo());
            return postDao.getDaysFavoritePosts(pageNo, this.postCountInPage);
        } catch(Exception e) {
            return null;
        }
    }
}
