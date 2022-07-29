package org.mytoypjt.service.post.strategy.pagecount;

public class DaysFavoriteStrategy extends BasePageCountStrategy{
    DaysFavoriteStrategy(int postCountInPage) {
        super(postCountInPage);
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
