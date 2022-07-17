package org.mytoypjt.service;

import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.entity.Post;

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

    public List<Post> getPosts(String pageNo, String sortType){
        if (pageNo == null || sortType == null)
            return null;




        return null;
    }

    public List<Post> getDefaultPosts(int pageNo){
        List<Post> postList = postDao.getPosts(pageNo);
        return postList;
    }





}
