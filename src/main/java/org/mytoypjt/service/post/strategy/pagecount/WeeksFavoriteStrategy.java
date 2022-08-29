package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;

public class WeeksFavoriteStrategy extends BasePageCountStrategy{
    WeeksFavoriteStrategy() {
    }

    @Override
    public int getPageCount() {
        PostDao postDao = new PostDao(null);
        int postCount = postDao.getWeeksPostCount(this.postCountInPage);
        int pageCount = (int) postCount / this.postCountInPage;
        if (postCount % this.postCountInPage != 0)
            pageCount++;
        return pageCount;
    }
}
