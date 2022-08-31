package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.models.dto.PostSortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PageCountStrategyFactory {
    private Map<PostSortType, BasePageCountStrategy> strategyMap;

    public PageCountStrategyFactory(List<BasePageCountStrategy> strategies) {
        strategyMap = new HashMap<>();
        strategies.forEach(strategy -> strategyMap.put(strategy.getSortType(), strategy));
    }
    
    public BasePageCountStrategy getInstance(PostSortType postSortType) {
        return this.strategyMap.get(postSortType);
    }
}
