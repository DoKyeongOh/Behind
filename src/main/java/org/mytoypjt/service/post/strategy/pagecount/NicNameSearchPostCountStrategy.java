package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.vo.PostOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NicNameSearchPostCountStrategy extends BasePostCountStrategy{

    @Autowired
    PostDao postDao;

    public NicNameSearchPostCountStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.SEARCH_BY_NICNAME;
    }

    @Override
    public int getPostCount(PostOption options) {
        return 0;
    }
}
