package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostsOptionVO;

import java.util.List;
import java.util.Map;

public class PostsStrategyContext {

    private BasePostsStrategy postsStrategy;

    private int postCountInPage = 12;

    public PostsStrategyContext() {
    }

    public PostsStrategyContext(int postCountInPage) {
        this.postCountInPage = postCountInPage;
    }

    public void setPostsStrategy(BasePostsStrategy postsStrategy){
        this.postsStrategy = postsStrategy;
    }

    public void setPostsStrategy(PostSortType sortType){
        switch (sortType) {
            case REAL_TIME: setPostsStrategy(new RealTimeStrategy(this.postCountInPage));break;
            case DAYS_FAVORITE: setPostsStrategy(new DaysFavoriteStrategy(this.postCountInPage)); break;
            case WEEKS_FAVORITE: setPostsStrategy(new WeeksFavoriteStrategy(this.postCountInPage)); break;
            case SEARCH_TITLE_FROM_USER: setPostsStrategy(new TitleSearchStrategy(this.postCountInPage)); break;
            case SEARCH_CONTENT_FROM_USER: setPostsStrategy(new ContentSearchStrategy(this.postCountInPage)); break;
            case HASH_TAG: setPostsStrategy(new HashTagStrategy(this.postCountInPage)); break;
        }
    }

    public List<Post> getPosts(PostsOptionVO postsOptionVO, Map<String, String[]> paramMap){
        return this.postsStrategy.getPosts(postsOptionVO, paramMap);
    }

}
