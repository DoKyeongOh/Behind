package org.mytoypjt.service.post.strategy.posts;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.vo.PostOption;
import org.mytoypjt.service.post.PostConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NicNameSearchPostsStrategy extends BasePostsStrategy {

    @Autowired
    PostDao postDao;

    public NicNameSearchPostsStrategy() {
    }

    @Override
    public PostSortType getSortType() {
        return PostSortType.SEARCH_BY_NICNAME;
    }

    @Override
    public List<Post> getPosts(PostOption optionVO) {
        String searchWord = optionVO.getOptionMap().get(PostConst.SEARCH_WORD);
        int pageNo = Integer.parseInt(optionVO.getPageNo());

        return postDao.getPostsByNicName(pageNo, optionVO.getPostCountLimitInPage(), searchWord);
    }
}
