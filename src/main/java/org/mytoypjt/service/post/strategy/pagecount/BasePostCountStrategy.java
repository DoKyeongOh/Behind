package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.vo.PostOption;

public abstract class BasePostCountStrategy {

    BasePostCountStrategy(){
    }

    public abstract PostSortType getSortType();

    public abstract int getPostCount();

}
