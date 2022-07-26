package org.mytoypjt.service;

import org.mytoypjt.dao.CommentDao;
import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.vo.PostsOptionVO;
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

    final int POST_COUNT_IN_PAGE = 12;
    final int PAGE_COUNT_IN_PAGE = 5;

    private PostDao postDao;
    private CommentDao commentDao;

    public PostService(){
        postDao = new PostDao();
        commentDao = new CommentDao();
    }

    public List<Post> getPosts(PostsOptionVO options){
        int pageNo = getPostPage(options.getPageNo());
        PostSortType sortType = getPostSortType(options.getSortType());

        List<Post> postList = postDao.getPosts(sortType, pageNo, POST_COUNT_IN_PAGE);

        return postList;
    }

    public PostsOptionVO getNewPostsOption(PostsOptionVO optionInRequest, PostsOptionVO optionInSession){
        String sortType = "";
        String pageNo = "";

        if (!optionInRequest.getSortType().isEmpty() && !optionInSession.getSortType().isEmpty()) {
            sortType = optionInRequest.getSortType();
            pageNo = optionInRequest.getPageNo();
        } else if (optionInRequest.getSortType().isEmpty() && !optionInSession.getSortType().isEmpty()) {
            sortType = optionInSession.getSortType();
            pageNo = optionInRequest.getPageNo();
        } else if (!optionInRequest.getSortType().isEmpty() && optionInSession.getSortType().isEmpty()) {
            sortType = optionInRequest.getSortType();
            pageNo = optionInSession.getPageNo();
        }

        if (pageNo.isEmpty())
            pageNo = "1";
        if (sortType.isEmpty())
            sortType = "1";

        int pageTotalCount = 1;
        PostSortType type = getPostSortType(sortType);
        switch (type) {
            case REAL_TIME: pageTotalCount = getRealTimePageCount(); break;
            case DAYS_FAVORITE: pageTotalCount = getDaysPageCount(); break;
            case WEEKS_FAVORITE: pageTotalCount = getWeeksPageCount(); break;
        }

        PostsOptionVO options = new PostsOptionVO(pageNo, sortType);
        options.setStartEndPageNo(pageTotalCount, 5);

        return options;
    }

    public PostsOptionVO getDefaultPostsOption() {
        int pageTotalCount = getRealTimePageCount();
        PostsOptionVO options = new PostsOptionVO("1", "1");
        options.setStartEndPageNo(pageTotalCount, 5);
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
            int commentCount = commentDao.getCommentCount(no);
            commentDao.updateCommentCount(no, commentCount);
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

    public int getRealTimePageCount() {
        int postCount = postDao.getPostCount();
        int pageCount = (int) postCount / POST_COUNT_IN_PAGE;
        if (postCount % POST_COUNT_IN_PAGE != 0)
            pageCount++;
        return pageCount;
    }

    public int getDaysPageCount(){
        int postCount = postDao.getDaysPostCount(POST_COUNT_IN_PAGE);
        int pageCount = (int) postCount / POST_COUNT_IN_PAGE;
        if (postCount % POST_COUNT_IN_PAGE != 0)
            pageCount++;
        return pageCount;
    }

    public int getWeeksPageCount(){
        int postCount = postDao.getWeeksPostCount(POST_COUNT_IN_PAGE);
        int pageCount = (int) postCount / POST_COUNT_IN_PAGE;
        if (postCount % POST_COUNT_IN_PAGE != 0)
            pageCount++;
        return pageCount;
    }

    public void createPost(Profile profile, String title, String content, String isAnonName, String isAnonCity, String img) {
        if (isNull(title, content, profile)) return;

        boolean isAnonymousName = false;
        if (!isNull(isAnonName) && isAnonName.equals("true"))
            isAnonymousName = true;

        boolean isAnonymousCity = false;
        if (!isNull(isAnonCity) && isAnonCity.equals("true"))
            isAnonymousCity = true;

        int imgNo = 1;

        try {
            imgNo = Integer.parseInt(img);
        } catch (Exception e) {

        }

        postDao.createPost(profile, title, content, isAnonymousName, isAnonymousCity, imgNo);
    }
}
