package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.models.dto.PostSortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PostsStrategyContext {

    Map<PostSortType, BasePostsStrategy> strategyMap;

    public PostsStrategyContext(@Autowired ApplicationContext ac) {
        strategyMap = new HashMap<>();
        strategyMap.put(PostSortType.REAL_TIME, ac.getBean(RealTimePostsStrategy.class));
        strategyMap.put(PostSortType.DAYS_FAVORITE, ac.getBean(DaysFavoritePostsStrategy.class));
        strategyMap.put(PostSortType.WEEKS_FAVORITE, ac.getBean(WeeksFavoritePostsStrategy.class));
        strategyMap.put(PostSortType.SEARCH_TITLE_FROM_USER, ac.getBean(TitleSearchPostsStrategy.class));
        strategyMap.put(PostSortType.SEARCH_CONTENT_FROM_USER, ac.getBean(ContentSearchPostsStrategy.class));
        strategyMap.put(PostSortType.HASH_TAG, ac.getBean(HashTagPostsStrategy.class));
    }

    public BasePostsStrategy getInstance(PostSortType postSortType){
        return this.strategyMap.get(postSortType);
    }


}
