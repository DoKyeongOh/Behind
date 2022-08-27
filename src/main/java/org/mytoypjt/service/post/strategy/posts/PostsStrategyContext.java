package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PostsStrategyContext {

    private BasePostsStrategy postsStrategy;

    public PostsStrategyContext() {
    }

    private int postCountInPage = 12;

    public int getPostCountInPage() {
        return postCountInPage;
    }

    public void setPostCountInPage(int postCountInPage) {
        this.postCountInPage = postCountInPage;
    }

    public void setPostsStrategy(BasePostsStrategy postsStrategy){
        this.postsStrategy = postsStrategy;
    }

    public void setPostsStrategy(PostSortType sortType){
        switch (sortType) {
            case REAL_TIME: {
                setPostsStrategy(new RealTimeStrategy());
                break;
            }
            case DAYS_FAVORITE: {
                setPostsStrategy(new DaysFavoriteStrategy());
                break;
            }
            case WEEKS_FAVORITE: {
                setPostsStrategy(new WeeksFavoriteStrategy());
                break;
            }
            case SEARCH_TITLE_FROM_USER: {
                setPostsStrategy(new TitleSearchStrategy());
                break;
            }
            case SEARCH_CONTENT_FROM_USER: {
                setPostsStrategy(new ContentSearchStrategy());
                break;
            }
            case HASH_TAG: {
                setPostsStrategy(new HashTagStrategy());
                break;
            }
        }
    }

    public List<Post> getPosts(PostsOptionVO postsOptionVO, Map<String, String> paramMap){
        this.postsStrategy.setPostCountInPage(this.postCountInPage);
        return this.postsStrategy.getPosts(postsOptionVO, paramMap);
    }

}
