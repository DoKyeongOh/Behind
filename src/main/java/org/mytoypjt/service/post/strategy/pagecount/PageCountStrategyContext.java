package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.vo.PostsOptionVO;

import java.util.Map;

public class PageCountStrategyContext {

    private BasePageCountStrategy pageCountStrategy;

    private int postCountInPage = 12;

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
            case REAL_TIME: setPageCountStrategy(new RealTimeStrategy(this.postCountInPage)); break;
            case DAYS_FAVORITE: setPageCountStrategy(new DaysFavoriteStrategy(this.postCountInPage)); break;
            case WEEKS_FAVORITE: setPageCountStrategy(new WeeksFavoriteStrategy(this.postCountInPage)); break;
            case SEARCH_TITLE_FROM_USER: setPageCountStrategy(new TitleSearchStrategy(this.postCountInPage)); break;
            case SEARCH_CONTENT_FROM_USER: setPageCountStrategy(new ContentSearchStrategy(this.postCountInPage)); break;
            case HASH_TAG: setPageCountStrategy(new HashTagStrategy(this.postCountInPage)); break;
        }
    }

    public int getPageCount(){
        return this.pageCountStrategy.getPageCount();
    }
}
