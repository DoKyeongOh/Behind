package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.vo.PostOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TitleSearchPostCountStrategy extends BasePostCountStrategy {

    @Autowired
    PostDao postDao;

    public TitleSearchPostCountStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.SEARCH_TITLE_FROM_USER;
    }

    @Override
    public int getPostCount() {
        return 0;
    }
}
