package org.mytoypjt.service.post.strategy.pagecount;

public class TitleSearchStrategy extends BasePageCountStrategy{
    TitleSearchStrategy(int postCountInPage) {
        super(postCountInPage);
    }

    @Override
    public int getPageCount() {
        return 0;
    }
}
