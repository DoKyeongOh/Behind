package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;

public abstract class BasePageCountStrategy {

    int postCountInPage = 12;

    protected PostDao postDao;

    BasePageCountStrategy(int postCountInPage){
        this.postCountInPage = postCountInPage;
        postDao = new PostDao();
    }

    public abstract int getPageCount();

}
