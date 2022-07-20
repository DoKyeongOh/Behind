package org.mytoypjt.service;

import org.mytoypjt.dao.CommentDao;
import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.etc.PostSortType;

import java.util.List;

public class PostViewService {

    final int PICTURE_COUNT = 10;
    final int SORT_REALTIME = 1;
    final int SORT_DAYS_LIKE = 2;
    final int SORT_WEEKS_LIKE = 3;

    int page;
    private PostDao postDao;;
    private CommentDao commentDao;

    public PostViewService(){
        postDao = new PostDao();
        commentDao = new CommentDao();
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


    public Post getPost(String no) {
        if (no == null)
            return null;

        int postNo = -1;
        try {
            postNo = Integer.parseInt(no);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Post post = postDao.getPost(postNo);
        return post;
    }

    public List<Comment> getComments(int postNo) {
        return commentDao.getComments(postNo);
    }
}
