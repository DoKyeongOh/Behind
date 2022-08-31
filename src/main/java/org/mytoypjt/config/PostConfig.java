package org.mytoypjt.config;

import org.mytoypjt.service.post.strategy.pagecount.*;
import org.mytoypjt.service.post.strategy.posts.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PostConfig {

    @Bean
    public List<BasePageCountStrategy> pageCountStrategies(){
        List<BasePageCountStrategy> strategies = new ArrayList<>();
        strategies.add(new RealTimePageCountStrategy());
        strategies.add(new DaysFavoritePageCountStrategy());
        strategies.add(new WeeksFavoritePageCountStrategy());
        strategies.add(new TitleSearchPageCountStrategy());
        strategies.add(new ContentSearchPageCountStrategy());
        strategies.add(new HashTagPageCountStrategy());
        return strategies;
    }

    @Bean
    public List<BasePostsStrategy> postsStrategies(){
        List<BasePostsStrategy> strategies = new ArrayList<>();
        strategies.add(new RealTimePostsStrategy());
        strategies.add(new DaysFavoritePostsStrategy());
        strategies.add(new WeeksFavoritePostsStrategy());
        strategies.add(new TitleSearchPostsStrategy());
        strategies.add(new ContentSearchPostsStrategy());
        strategies.add(new HashTagPostsStrategy());
        return strategies;
    }
}
