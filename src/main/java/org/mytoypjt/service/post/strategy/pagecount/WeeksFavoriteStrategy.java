package org.mytoypjt.service.post.strategy.pagecount;

public class WeeksFavoriteStrategy extends BasePageCountStrategy{
    WeeksFavoriteStrategy(int postCountInPage) {
        super(postCountInPage);
    }

    @Override
    public int getPageCount() {
        int postCount = postDao.getWeeksPostCount(this.postCountInPage);
        int pageCount = (int) postCount / this.postCountInPage;
        if (postCount % this.postCountInPage != 0)
            pageCount++;
        return pageCount;
    }
}
