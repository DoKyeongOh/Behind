package org.mytoypjt.service.post.strategy.pagecount;

public class ContentSearchStrategy extends BasePageCountStrategy{
    ContentSearchStrategy(int postCountInPage) {
        super(postCountInPage);
    }

    @Override
    public int getPageCount() {
        return 0;
    }
}
