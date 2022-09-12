package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.vo.PostOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HashTagPostCountStrategy extends BasePostCountStrategy {

    @Autowired
    PostDao postDao;

    public HashTagPostCountStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.HASH_TAG;
    }

    @Override
    public int getPostCount() {
        return 0;
    }
}
