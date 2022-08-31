package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaysFavoritePageCountStrategy extends BasePageCountStrategy{

    @Autowired
    PostDao postDao;

    public DaysFavoritePageCountStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.DAYS_FAVORITE;
    }

    @Override
    public int getPageCount() {
        int postCount = postDao.getDaysPostCount(this.postCountInPage);
        int pageCount = (int) postCount / this.postCountInPage;
        if (postCount % this.postCountInPage != 0)
            pageCount++;
        return pageCount;
    }
}
