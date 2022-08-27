package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
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

    public abstract int getPageCount();

}
