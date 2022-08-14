package org.mytoypjt.service.post;

import org.mytoypjt.dao.*;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.entity.Reply;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.mytoypjt.service.annotation.Transaction;
import org.mytoypjt.service.post.strategy.pagecount.PageCountStrategyContext;
import org.mytoypjt.service.post.strategy.posts.PostsStrategyContext;
import org.mytoypjt.utils.TransactionManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostService {
    private PageCountStrategyContext pageCountStrategyContext;
    private PostsStrategyContext postsStrategyContext;

    final int PICTURE_COUNT = 10;
    final int SORT_REALTIME = 1;
    final int SORT_DAYS_LIKE = 2;
    final int SORT_WEEKS_LIKE = 3;

    final int POST_COUNT_IN_PAGE = 12;
    final int PAGE_COUNT_IN_PAGE = 5;

    public PostService(){
        this.pageCountStrategyContext = (PageCountStrategyContext)
                TransactionManager.getInstance(PageCountStrategyContext.class);
        pageCountStrategyContext.setPostCountInPage(POST_COUNT_IN_PAGE);

        this.postsStrategyContext = (PostsStrategyContext)
                TransactionManager.getInstance(PostsStrategyContext.class);
        postsStrategyContext.setPostCountInPage(POST_COUNT_IN_PAGE);
    }

    @Transaction
    public Profile getPosterProfile(int accountNo) {
        Profile profile = new ProfileDao().getProfile(accountNo);
        if (profile == null) {
            profile = new Profile(accountNo);
            profile.setNicname("익명");
            profile.setCity("미등록 지역");
        }
        return profile;
    }

    public List<String> getPostersCity(List<Post> posts) {
        if (posts == null) return null;

        List<String> cities = new ArrayList<String>();

        posts.forEach((post) -> {
            int profileNo = post.getAccountNo();
            cities.add(post.getCity());
        });
        return cities;
    }

    public PostsOptionVO createPostsOption(PostsOptionVO optionInRequest, PostsOptionVO optionInSession){
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

        PostSortType type = getPostSortType(sortType);

        pageCountStrategyContext.setPageCountStrategy(type);
        int pageTotalCount = pageCountStrategyContext.getPageCount();

        PostsOptionVO options = new PostsOptionVO(pageNo, sortType);
        options.setStartEndPageNo(pageTotalCount, 5);

        return options;
    }

    public PostsOptionVO getDefaultPostsOption() {
        pageCountStrategyContext.setPageCountStrategy(PostSortType.REAL_TIME);
        int pageTotalCount = pageCountStrategyContext.getPageCount();

        PostsOptionVO options = new PostsOptionVO("1", "1");
        options.setStartEndPageNo(pageTotalCount, PAGE_COUNT_IN_PAGE);
        return options;
    }

    public List<Post> getPosts(PostsOptionVO options, Map<String, String[]> paramMap){
        PostSortType sortType = getPostSortType(options.getSortType());
        postsStrategyContext.setPostsStrategy(sortType);
        return postsStrategyContext.getPosts(options, paramMap);
    }

    @Transaction
    public Post getPost(String no) {
        if (no == null)
            return null;

        int postNo = Integer.parseInt(no);
        return new PostDao().getPost(postNo);
    }

    @Transaction
    public void createPost(Profile profile, Post post) {
        new PostDao().createPost(profile, post);
        new PostLogDao().writePostActivityLog(profile.getAccountNo(), post.getPostNo(), "게시");
    }

    @Transaction
    public void updatePost(Post post) {
        new PostDao().updatePost(post);
    }

    @Transaction
    public void toggleLike(String post, String account) {
        if (post == null) return;
        if (account == null) return;

        int postNo = Integer.parseInt(post);
        int accountNo = Integer.parseInt(account);

        PostDao postDao = new PostDao();
        if (postDao.isAlreadyLikeThis(postNo, accountNo))
            postDao.delLike(postNo, accountNo);
        else
            postDao.addLike(postNo, accountNo);

        int likeCount = postDao.getLikeCount(postNo);
        postDao.updateLikeCount(postNo, likeCount);
    }

    @Transaction
    public boolean isLikePost(String postNo, String accountNo) {
        if (postNo == null)
            return false;
        if (accountNo == null)
            return false;
        try {
            return new PostDao().isUserLikePost(
                    Integer.parseInt(postNo),
                    Integer.parseInt(accountNo)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transaction
    public List<Comment> getComments(int postNo) {
        return new CommentDao().getComments(postNo);
    }

    @Transaction
    public void createComment(String postNo, Profile profile, String isUseAnonymousName, String content) {
        if (isNull(postNo, profile, content))
            return;

        if (isUseAnonymousName == null)
            isUseAnonymousName = "off";

        boolean isAnonymous = false;
        if (isUseAnonymousName.equals("on"))
            isAnonymous = true;

        try {
            CommentDao commentDao = new CommentDao();
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

    @Transaction
    public Comment getComment(String no){
        try {
            int commentNo = Integer.parseInt(no);
            CommentDao commentDao = new CommentDao();
            return commentDao.getCommentByCommentNo(commentNo);
        } catch (Exception e) {
            return null;
        }
    }

    @Transaction
    public List<Reply> getReplies(Comment comment) {
        int commentNo = comment.getCommentNo();
        String nicname = comment.getNicname();

        ReplyDao replyDao = new ReplyDao();
        return replyDao.getReplies(commentNo);
    }

    @Transaction
    public void createReply(String content, String replierNo, String commentNo, String isAnonName) {
        int accountNo = Integer.parseInt(replierNo);
        int commentNoInt = Integer.parseInt(commentNo);

        boolean isAnonymousName = true;
        if (isAnonName == null)
            isAnonymousName = false;

        ProfileDao profileDao = new ProfileDao();
        Profile profile = profileDao.getProfile(accountNo);
        new ReplyDao().createReply(content, profile, commentNoInt, isAnonymousName);
    }

    public boolean isNull(Object...param) {
        for (Object str : param) {
            if (str == null)
                return true;
        }
        return false;
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

}
