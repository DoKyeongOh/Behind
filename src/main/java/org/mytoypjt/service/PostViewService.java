package org.mytoypjt.service;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.etc.PostSortType;

import java.util.List;

public class PostViewService {

    final int PICTURE_COUNT = 10;
    final int SORT_REALTIME = 1;
    final int SORT_DAYS_LIKE = 2;
    final int SORT_WEEKS_LIKE = 3;

    int page;
    private PostDao postDao;

    public PostViewService(){
        postDao = new PostDao();
    }

    public List<Post> getPosts(String page, String type){
        if (page == null)
            page = "1";
        if (type == null)
            type = "1";

        int pageNo = Integer.parseInt(page);
        PostSortType sortType = getPostSortType(type);

        List<Post> postList = postDao.getPosts(sortType, pageNo);

        return postList;
    }

    public PostSortType getPostSortType(String sortType){
        switch (sortType) {
            case "1": return PostSortType.REAL_TIME;
            case "2": return PostSortType.DAYS_FAVORITE;
            case "3": return PostSortType.WEEKS_FAVORITE;
        }
        return PostSortType.REAL_TIME;
    }

    public List<Post> getDefaultPosts(int pageNo){
        List<Post> postList = postDao.getPosts(PostSortType.REAL_TIME, pageNo);
        return postList;
    }





}
