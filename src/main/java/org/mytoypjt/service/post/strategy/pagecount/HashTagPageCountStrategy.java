package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HashTagPageCountStrategy extends BasePageCountStrategy{

    @Autowired
    PostDao postDao;

    public HashTagPageCountStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.HASH_TAG;
    }

    @Override
    public int getPageCount(PostsOptionVO postsOptionVO) {
        return 0;
    }
}
