package org.mytoypjt.service;

import org.mytoypjt.dao.CommentDao;
import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.etc.PostSortType;

import java.util.List;

public class PostService {

    final int PICTURE_COUNT = 10;
    final int SORT_REALTIME = 1;
    final int SORT_DAYS_LIKE = 2;
    final int SORT_WEEKS_LIKE = 3;

    int page;
    private PostDao postDao;
    private CommentDao commentDao;

    public PostService(){
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
        if (sortType == null)
            return PostSortType.REAL_TIME;

        switch (sortType) {
            case "1": return PostSortType.REAL_TIME;
            case "2": return PostSortType.DAYS_FAVORITE;
            case "3": return PostSortType.WEEKS_FAVORITE;
            default: return PostSortType.REAL_TIME;
        }
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

    public void toggleLike(String post, String account) {
        if (post == null) return;
        if (account == null) return;

        int postNo = -1;
        int accountNo = -1;

        try {
            postNo = Integer.parseInt(post);
            accountNo = Integer.parseInt(account);
        } catch (Exception e) {
            return;
        }

        if (postDao.isAlreadyLikeThis(postNo, accountNo))
            postDao.delLike(postNo, accountNo);
        else
            postDao.addLike(postNo, accountNo);

        int likeCount = postDao.getLikeCount(postNo);
        postDao.updateLikeCount(postNo, likeCount);
    }

    public boolean isLikePost(String postNo, String accountNo) {
        if (postNo == null)
            return false;
        if (accountNo == null)
            return false;

        try {
            return postDao.isUserLikePost(
                    Integer.parseInt(postNo),
                    Integer.parseInt(accountNo)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLikePost(String postNo, int accountNo) {
        if (postNo == null)
            return false;

        try {
            return postDao.isUserLikePost(
                    Integer.parseInt(postNo),
                    accountNo
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void createComment(String postNo, Profile profile, String isUseAnonymousName, String content) {
        if (isNull(postNo, profile, content))
            return;

        if (isUseAnonymousName == null)
            isUseAnonymousName = "off";

        boolean isAnonymous = false;
        if (isUseAnonymousName.equals("on"))
            isAnonymous = true;

        try {
            commentDao.createComment(
                    Integer.parseInt(postNo),
                    profile,
                    isAnonymous,
                    content
            );

            int no = Integer.parseInt(postNo);
            int commentCount = postDao.getCommentCount(no);
            postDao.updateCommentCount(no, commentCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isNull(Object...param) {
        for (Object str : param) {
            if (str == null)
                return true;
        }
        return false;
    }
}
