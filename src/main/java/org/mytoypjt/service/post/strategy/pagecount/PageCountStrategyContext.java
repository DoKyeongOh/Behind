package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.models.dto.PostSortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PageCountStrategyContext {
    private Map<PostSortType, BasePageCountStrategy> strategyMap;

    public PageCountStrategyContext(@Autowired ApplicationContext ac) {
        strategyMap = new HashMap<>();
        strategyMap.put(PostSortType.REAL_TIME, ac.getBean(RealTimePageCountStrategy.class));
        strategyMap.put(PostSortType.DAYS_FAVORITE, ac.getBean(DaysFavoritePageCountStrategy.class));
        strategyMap.put(PostSortType.WEEKS_FAVORITE, ac.getBean(WeeksFavoritePageCountStrategy.class));
        strategyMap.put(PostSortType.SEARCH_TITLE_FROM_USER, ac.getBean(TitleSearchPageCountStrategy.class));
        strategyMap.put(PostSortType.SEARCH_CONTENT_FROM_USER, ac.getBean(ContentSearchPageCountStrategy.class));
        strategyMap.put(PostSortType.HASH_TAG, ac.getBean(HashTagPageCountStrategy.class));
        
    }

    public void setStrategyMap() {
    }
    
    public BasePageCountStrategy getStrategy(PostSortType postSortType) {
        return this.strategyMap.get(postSortType);
    }
}
