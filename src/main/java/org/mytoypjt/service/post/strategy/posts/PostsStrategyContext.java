package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.mytoypjt.utils.TransactionManager;

import java.util.List;
import java.util.Map;

public class PostsStrategyContext {

    private BasePostsStrategy postsStrategy;

    public PostsStrategyContext() {
    }

    public PostsStrategyContext(int postCountInPage) {
        this.postCountInPage = postCountInPage;
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
                setPostsStrategy((BasePostsStrategy) TransactionManager.getInstance(RealTimeStrategy.class));
                break;
            }
            case DAYS_FAVORITE: {
                setPostsStrategy((BasePostsStrategy) TransactionManager.getInstance(DaysFavoriteStrategy.class));
                break;
            }
            case WEEKS_FAVORITE: {
                setPostsStrategy((BasePostsStrategy) TransactionManager.getInstance(WeeksFavoriteStrategy.class));
                break;
            }
            case SEARCH_TITLE_FROM_USER: {
                setPostsStrategy((BasePostsStrategy) TransactionManager.getInstance(TitleSearchStrategy.class));
                break;
            }
            case SEARCH_CONTENT_FROM_USER: {
                setPostsStrategy((BasePostsStrategy) TransactionManager.getInstance(ContentSearchStrategy.class));
                break;
            }
            case HASH_TAG: {
                setPostsStrategy((BasePostsStrategy) TransactionManager.getInstance(HashTagStrategy.class));
                break;
            }
        }
    }

    public List<Post> getPosts(PostsOptionVO postsOptionVO, Map<String, String> paramMap){
        this.postsStrategy.setPostCountInPage(this.postCountInPage);
        return this.postsStrategy.getPosts(postsOptionVO, paramMap);
    }

}
