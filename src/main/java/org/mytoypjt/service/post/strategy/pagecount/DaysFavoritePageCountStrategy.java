package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaysFavoritePageCountStrategy extends BasePageCountStrategy{

    @Autowired
    PostDao postDao;

    public DaysFavoritePageCountStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.DAYS_FAVORITE;
    }

    @Override
    public int getPageCount(PostsOptionVO postsOptionVO) {
        int postCountLimitInPage = postsOptionVO.getPostCountInPage();
        int allPostCount = postDao.getDaysPostCount(postCountLimitInPage);
        int pageCount = (allPostCount / postCountLimitInPage) + 1;
        return pageCount;
    }
}
