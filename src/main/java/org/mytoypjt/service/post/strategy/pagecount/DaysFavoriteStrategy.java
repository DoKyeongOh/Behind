package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.service.annotation.Transaction;
import org.mytoypjt.utils.TransactionManager;

public class DaysFavoriteStrategy extends BasePageCountStrategy{
    DaysFavoriteStrategy() {
    }

    @Override
    public int getPageCount() {
        PostDao postDao = new PostDao(getConnection());
        int postCount = postDao.getDaysPostCount(this.postCountInPage);
        int pageCount = (int) postCount / this.postCountInPage;
        if (postCount % this.postCountInPage != 0)
            pageCount++;
        return pageCount;
    }
}
