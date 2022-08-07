package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;

public abstract class BasePageCountStrategy {

    int postCountInPage = 12;

    BasePageCountStrategy(int postCountInPage){
        this.postCountInPage = postCountInPage;
    }

    public abstract int getPageCount();

}
