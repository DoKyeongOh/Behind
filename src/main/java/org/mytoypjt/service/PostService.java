package org.mytoypjt.service;

import org.mytoypjt.dao.CommentDao;
import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.dto.PostsOptionDto;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.dto.PostSortType;

import java.util.List;

public class PostService {

    final int PICTURE_COUNT = 10;
    final int SORT_REALTIME = 1;
    final int SORT_DAYS_LIKE = 2;
    final int SORT_WEEKS_LIKE = 3;

    private PostDao postDao;
    private CommentDao commentDao;

    public PostService(){
        postDao = new PostDao();
        commentDao = new CommentDao();
    }

    public List<Post> getPosts(PostsOptionDto options){
        int pageNo = getPostPage(options.getPageNo());
        PostSortType sortType = getPostSortType(options.getSortType());

        List<Post> postList = postDao.getPosts(sortType, pageNo);

        return postList;
    }

    public PostsOptionDto getPostsOption(PostsOptionDto optionInRequest, PostsOptionDto optionInSession){
        String sortType = "";
        String pageNo = "";

        if (!optionInRequest.getSortType().isEmpty() && !optionInSession.getSortType().isEmpty()) {
            sortType = optionInRequest.getSortType();
            pageNo = "1";
        } else if (optionInRequest.getSortType().isEmpty() && !optionInSession.getSortType().isEmpty()) {
            sortType = optionInSession.getSortType();
            pageNo = optionInRequest.getPageNo();
        } else if (!optionInRequest.getSortType().isEmpty() && optionInSession.getSortType().isEmpty()) {
            sortType = optionInRequest.getSortType();
            pageNo = optionInSession.getPageNo();
        }

        if (sortType.isEmpty())
            sortType = "1";

        if (pageNo.isEmpty())
            pageNo = "1";

        PostsOptionDto options = new PostsOptionDto(pageNo, sortType);
        return options;
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

    public int getPostPage(String pageNo){
        try {
            return Integer.parseInt(pageNo);
        } catch (Exception e) {
            return 1;
        }
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

    public int getPageCount() {
        int postCount = postDao.getPostCount();
        int pageCount = (int) postCount / postDao.getPictureCountInPage();
        if (pageCount == 0) pageCount = 1;
        if (pageCount > 5) pageCount = 5;
        return pageCount;
    }
}
