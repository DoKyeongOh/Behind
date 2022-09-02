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
public class WeeksFavoritePostsStrategy extends BasePostsStrategy {

    @Autowired
    PostDao postDao;

    public WeeksFavoritePostsStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.WEEKS_FAVORITE;
    }

    @Override
    public List<Post> getPosts(PostsOptionVO optionVO, Map<String, String> paramMap) {
        try {
            int pageNo = Integer.parseInt(optionVO.getPageNo());
            return postDao.getWeeksFavoritePosts(pageNo, this.postCountInPage);
        } catch(Exception e) {
            return null;
        }
    }
}
