package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.models.dto.PostSortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PostCountStrategyContext {
    private Map<PostSortType, BasePostCountStrategy> strategyMap;

    public PostCountStrategyContext(@Autowired ApplicationContext ac) {
        strategyMap = new HashMap<>();
        strategyMap.put(PostSortType.REAL_TIME, ac.getBean(RealTimePostCountStrategy.class));
        strategyMap.put(PostSortType.DAYS_FAVORITE, ac.getBean(DaysFavoritePostCountStrategy.class));
        strategyMap.put(PostSortType.WEEKS_FAVORITE, ac.getBean(WeeksFavoritePostCountStrategy.class));
        strategyMap.put(PostSortType.SEARCH_TITLE_FROM_USER, ac.getBean(TitleSearchPostCountStrategy.class));
        strategyMap.put(PostSortType.SEARCH_CONTENT_FROM_USER, ac.getBean(ContentSearchPostCountStrategy.class));
        strategyMap.put(PostSortType.HASH_TAG, ac.getBean(HashTagPostCountStrategy.class));
        
    }
    
    public BasePostCountStrategy getStrategy(PostSortType postSortType) {
        return this.strategyMap.get(postSortType);
    }
}
