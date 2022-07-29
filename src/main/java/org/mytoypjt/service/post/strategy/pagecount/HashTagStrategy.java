package org.mytoypjt.service.post.strategy.pagecount;

public class HashTagStrategy extends BasePageCountStrategy{
    HashTagStrategy(int postCountInPage) {
        super(postCountInPage);
    }

    @Override
    public int getPageCount() {
        return 0;
    }
}
