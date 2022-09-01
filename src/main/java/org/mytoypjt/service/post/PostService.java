package org.mytoypjt.service.post;

import org.mytoypjt.dao.*;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.entity.Reply;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.mytoypjt.service.post.strategy.pagecount.BasePageCountStrategy;
import org.mytoypjt.service.post.strategy.pagecount.PageCountStrategyFactory;
import org.mytoypjt.service.post.strategy.posts.BasePostsStrategy;
import org.mytoypjt.service.post.strategy.posts.PostsStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PostService {

    @Autowired
    private PostDao postDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ReplyDao replyDao;
    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private PostLogDao postLogDao;
    @Autowired
    private LikeDao likeDao;
    @Autowired
    private PageCountStrategyFactory pageCountStrategyFactory;
    @Autowired
    private PostsStrategyFactory postsStrategyFactory;

    final int PICTURE_COUNT = 10;
    final int SORT_REALTIME = 1;
    final int SORT_DAYS_LIKE = 2;
    final int SORT_WEEKS_LIKE = 3;

    final int POST_COUNT_IN_PAGE = 12;
    final int PAGE_COUNT_IN_PAGE = 5;

    public PostService(){
    }

    public Profile getPosterProfile(int accountNo) {
        Profile profile = profileDao.getProfile(accountNo);
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
        BasePageCountStrategy strategy = pageCountStrategyFactory.getInstance(type);
        strategy.setPostCountInPage(POST_COUNT_IN_PAGE);
        int pageTotalCount = strategy.getPageCount();

        PostsOptionVO options = new PostsOptionVO(pageNo, sortType);
        options.setStartEndPageNo(pageTotalCount, 5);

        return options;
    }

    public PostsOptionVO getDefaultPostsOption() {
        BasePageCountStrategy strategy = pageCountStrategyFactory.getInstance(PostSortType.REAL_TIME);
        strategy.setPostCountInPage(POST_COUNT_IN_PAGE);
        int pageTotalCount = strategy.getPageCount();

        PostsOptionVO options = new PostsOptionVO("1", "1");
        options.setStartEndPageNo(pageTotalCount, PAGE_COUNT_IN_PAGE);
        return options;
    }

    public List<Post> getPosts(PostsOptionVO options, Map<String, String> paramMap){
        PostSortType sortType = getPostSortType(options.getSortType());
        BasePostsStrategy strategy = postsStrategyFactory.getInstance(sortType);
        strategy.setPostCountInPage(POST_COUNT_IN_PAGE);
        return strategy.getPosts(options, paramMap);
    }

    public Post getPost(String no) {
        int postNo = Integer.parseInt(no);
        return postDao.getPost(postNo);
    }

    public void createPost(Post post) {
        String nicname = post.getIsUseAnonymousName() ? "누군가" : post.getNicname();
        post.setNicname(nicname);

        String city = post.getIsUseAnonymousCity() ? "어딘가" : post.getCity();
        post.setCity(city);

        postDao.createPost(post);
        postLogDao.writePostActivityLog(post.getAccountNo(), post.getPostNo(), "게시");
    }

    public void updatePost(Post post) {
        postDao.updatePost(post);
    }

    public void toggleLike(String post, String account) {
        if (post == null) return;
        if (account == null) return;

        int postNo = Integer.parseInt(post);
        int accountNo = Integer.parseInt(account);

        if (likeDao.isAlreadyLikeThis(postNo, accountNo))
            likeDao.delLike(postNo, accountNo);
        else
            likeDao.addLike(postNo, accountNo);

        int likeCount = likeDao.getLikeCount(postNo);
        postDao.updateLikeCount(postNo, likeCount);
    }

    public boolean isLikePost(String postNo, String accountNo) {
        if (postNo == null)
            return false;
        if (accountNo == null)
            return false;
        try {
            return likeDao.isUserLikePost(
                    Integer.parseInt(postNo),
                    Integer.parseInt(accountNo)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Comment> getComments(int postNo) {
        return commentDao.getComments(postNo);
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

    public Comment getComment(String no){
        try {
            int commentNo = Integer.parseInt(no);
            return commentDao.getCommentByCommentNo(commentNo);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Reply> getReplies(Comment comment) {
        int commentNo = comment.getCommentNo();
        return replyDao.getReplies(commentNo);
    }

    public void createReply(String content, String replierNo, String commentNo, String isAnonName) {
        int accountNo = Integer.parseInt(replierNo);
        int commentNoInt = Integer.parseInt(commentNo);

        boolean isAnonymousName = true;
        if (isAnonName == null)
            isAnonymousName = false;

        Profile profile = profileDao.getProfile(accountNo);
        replyDao.createReply(content, profile, commentNoInt, isAnonymousName);
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
