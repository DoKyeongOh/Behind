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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostService {
<<<<<<< HEAD
=======

    private Connection connection;

    PostDao postDao;
    CommentDao commentDao;
    ReplyDao replyDao;
    ProfileDao profileDao;
    PostLogDao postLogDao;

>>>>>>> transaction_test
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

        this.postDao = new PostDao(this.connection);
        this.commentDao = new CommentDao(this.connection);
        this.replyDao = new ReplyDao(this.connection);
        this.profileDao = new ProfileDao(this.connection);
        this.postLogDao = new PostLogDao(this.connection);
    }

    @Transaction
    public Profile getPosterProfile(int accountNo) {
<<<<<<< HEAD
        Profile profile = new ProfileDao().getProfile(accountNo);
=======
        Profile profile = profileDao.getProfile(accountNo);
>>>>>>> transaction_test
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
<<<<<<< HEAD
        if (no == null)
            return null;

        int postNo = Integer.parseInt(no);
        return new PostDao().getPost(postNo);
=======
        int postNo = Integer.parseInt(no);
        return postDao.getPost(postNo);
>>>>>>> transaction_test
    }

    @Transaction
    public void createPost(Profile profile, Post post) {
<<<<<<< HEAD
        new PostDao().createPost(profile, post);
        new PostLogDao().writePostActivityLog(profile.getAccountNo(), post.getPostNo(), "게시");
=======
        postDao.createPost(profile, post);
        postLogDao.writePostActivityLog(profile.getAccountNo(), post.getPostNo(), "게시");
>>>>>>> transaction_test
    }

    @Transaction
    public void updatePost(Post post) {
<<<<<<< HEAD
        new PostDao().updatePost(post);
=======
        postDao.updatePost(post);
>>>>>>> transaction_test
    }

    @Transaction
    public void toggleLike(String post, String account) {
        if (post == null) return;
        if (account == null) return;

        int postNo = Integer.parseInt(post);
        int accountNo = Integer.parseInt(account);

<<<<<<< HEAD
        PostDao postDao = new PostDao();
=======
>>>>>>> transaction_test
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
<<<<<<< HEAD
            return new PostDao().isUserLikePost(
=======
            return postDao.isUserLikePost(
>>>>>>> transaction_test
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
<<<<<<< HEAD
        return new CommentDao().getComments(postNo);
=======
        return commentDao.getComments(postNo);
>>>>>>> transaction_test
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
<<<<<<< HEAD
            CommentDao commentDao = new CommentDao();
=======
>>>>>>> transaction_test
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
<<<<<<< HEAD
            CommentDao commentDao = new CommentDao();
=======
>>>>>>> transaction_test
            return commentDao.getCommentByCommentNo(commentNo);
        } catch (Exception e) {
            return null;
        }
    }

    @Transaction
    public List<Reply> getReplies(Comment comment) {
        int commentNo = comment.getCommentNo();
<<<<<<< HEAD
        String nicname = comment.getNicname();

        ReplyDao replyDao = new ReplyDao();
=======
>>>>>>> transaction_test
        return replyDao.getReplies(commentNo);
    }

    @Transaction
    public void createReply(String content, String replierNo, String commentNo, String isAnonName) {
        int accountNo = Integer.parseInt(replierNo);
        int commentNoInt = Integer.parseInt(commentNo);

        boolean isAnonymousName = true;
        if (isAnonName == null)
            isAnonymousName = false;

<<<<<<< HEAD
        ProfileDao profileDao = new ProfileDao();
        Profile profile = profileDao.getProfile(accountNo);
        new ReplyDao().createReply(content, profile, commentNoInt, isAnonymousName);
=======
        Profile profile = profileDao.getProfile(accountNo);
        replyDao.createReply(content, profile, commentNoInt, isAnonymousName);
>>>>>>> transaction_test
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
