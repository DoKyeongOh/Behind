package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.models.dto.PostSortType;

public abstract class BasePageCountStrategy {

    int postCountInPage = 12;

    BasePageCountStrategy(){

    }

    public int getPostCountInPage() {
        return postCountInPage;
    }
    public void setPostCountInPage(int postCountInPage) {
        this.postCountInPage = postCountInPage;
    }

    public abstract PostSortType getSortType();

    public abstract int getPageCount();

}
