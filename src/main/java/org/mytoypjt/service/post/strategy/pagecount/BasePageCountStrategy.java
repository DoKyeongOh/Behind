package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.vo.PostsOptionVO;

public abstract class BasePageCountStrategy {

    BasePageCountStrategy(){
    }

    public abstract PostSortType getSortType();

    public abstract int getPageCount(PostsOptionVO postsOptionVO);

}
