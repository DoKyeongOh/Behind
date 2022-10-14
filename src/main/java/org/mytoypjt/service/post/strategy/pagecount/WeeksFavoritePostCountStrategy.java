package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.vo.PostOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeeksFavoritePostCountStrategy extends BasePostCountStrategy {

    @Autowired
    PostDao postDao;

    public WeeksFavoritePostCountStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.WEEKS_FAVORITE;
    }

    @Override
    public int getPostCount(PostOption options) {
        return postDao.getWeeksPostCount();
    }
}
