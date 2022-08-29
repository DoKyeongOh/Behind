package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;

public class RealTimeStrategy extends BasePageCountStrategy{

    RealTimeStrategy() {
    }

    @Override
    public int getPageCount() {
        PostDao postDao = new PostDao(null);
        int postCount = postDao.getTotalPostCount();
        int pageCount = (int) postCount / this.postCountInPage;
        if (postCount % this.postCountInPage != 0)
            pageCount++;
        return pageCount;
    }
}
