package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.vo.PostOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RealTimePostCountStrategy extends BasePostCountStrategy {
    @Autowired
    PostDao postDao;

    public RealTimePostCountStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.REAL_TIME;
    }

    @Override
    public int getPostCount(PostOption options) {
        return postDao.getTotalPostCount();
    }
}
