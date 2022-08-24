package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.models.dto.PostSortType;

import java.sql.Connection;

public class PageCountStrategyContext {

    private BasePageCountStrategy pageCountStrategy;

    private Connection connection;

    private int postCountInPage = 12;

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


    public PageCountStrategyContext() {
    }

    public PageCountStrategyContext(int postCountInPage) {
        this.postCountInPage = postCountInPage;
    }

    public void setPageCountStrategy(BasePageCountStrategy pageCountStrategy){
        this.pageCountStrategy = pageCountStrategy;
    }

    public void setPageCountStrategy(PostSortType sortType){
        switch (sortType) {
            case REAL_TIME: {
                setPageCountStrategy(new RealTimeStrategy());
                break;
            }
            case DAYS_FAVORITE: {
                setPageCountStrategy(new DaysFavoriteStrategy());
                break;
            }
            case WEEKS_FAVORITE: {
                setPageCountStrategy(new WeeksFavoriteStrategy());
                break;
            }
            case SEARCH_TITLE_FROM_USER: {
                setPageCountStrategy(new TitleSearchStrategy());
                break;
            }
            case SEARCH_CONTENT_FROM_USER: {
                setPageCountStrategy(new ContentSearchStrategy());
                break;
            }
            case HASH_TAG: {
                setPageCountStrategy(new HashTagStrategy());
                break;
            }
        }
    }

    @Transaction
    public int getPageCount(){
        this.pageCountStrategy.setConnection(getConnection());
        this.pageCountStrategy.setPostCountInPage(this.postCountInPage);
        return this.pageCountStrategy.getPageCount();
    }
}
