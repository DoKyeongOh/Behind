package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentSearchPageCountStrategy extends BasePageCountStrategy{

    @Autowired
    PostDao postDao;

    public ContentSearchPageCountStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.SEARCH_CONTENT_FROM_USER;
    }

    @Override
    public int getPageCount() {
        return 0;
    }
}
