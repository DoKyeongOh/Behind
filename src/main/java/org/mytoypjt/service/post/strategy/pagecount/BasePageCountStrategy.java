package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;

import java.sql.Connection;

public abstract class BasePageCountStrategy {

    int postCountInPage = 12;

    Connection connection;

    BasePageCountStrategy(){
    }

    public int getPostCountInPage() {
        return postCountInPage;
    }

    public void setPostCountInPage(int postCountInPage) {
        this.postCountInPage = postCountInPage;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public abstract int getPageCount();

}
