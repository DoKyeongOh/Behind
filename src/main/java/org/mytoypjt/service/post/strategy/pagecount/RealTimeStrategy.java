package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.service.annotation.Transaction;
import org.mytoypjt.utils.TransactionManager;

import java.sql.Connection;

public class RealTimeStrategy extends BasePageCountStrategy{

    RealTimeStrategy() {
    }

    @Override
    public int getPageCount() {
        PostDao postDao = new PostDao(getConnection());
        int postCount = postDao.getTotalPostCount();
        int pageCount = (int) postCount / this.postCountInPage;
        if (postCount % this.postCountInPage != 0)
            pageCount++;
        return pageCount;
    }
}
