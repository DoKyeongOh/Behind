package org.mytoypjt.service.post.strategy.pagecount;

public class RealTimeStrategy extends BasePageCountStrategy{
    RealTimeStrategy(int postCountInPage) {
        super(postCountInPage);
    }

    @Override
    public int getPageCount() {
        int postCount = postDao.getPostCount();
        int pageCount = (int) postCount / this.postCountInPage;
        if (postCount % this.postCountInPage != 0)
            pageCount++;
        return pageCount;
    }
}
