package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.models.dto.PostSortType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PostsStrategyFactory {

    Map<PostSortType, BasePostsStrategy> strategyMap;

    public PostsStrategyFactory(List<BasePostsStrategy> strategies) {
        strategyMap = new HashMap<>();
        strategies.forEach(strategy -> {
            strategyMap.put(strategy.getSortType(), strategy);
        });
    }

    public BasePostsStrategy getInstance(PostSortType postSortType){
        return this.strategyMap.get(postSortType);
    }


}
