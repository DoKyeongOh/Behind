package org.mytoypjt.service.post.strategy.pagecount;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.vo.PostOption;
import org.mytoypjt.service.post.PostConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentSearchPostCountStrategy extends BasePostCountStrategy {

    @Autowired
    PostDao postDao;

    public ContentSearchPostCountStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.SEARCH_BY_CONTENT;
    }

    @Override
    public int getPostCount(PostOption options) {
        String searchWord = options.getOptionMap().get(PostConst.SEARCH_WORD);
        if (searchWord == null)
            searchWord = "";

        return postDao.getPostCountByContent(searchWord);
    }

}
