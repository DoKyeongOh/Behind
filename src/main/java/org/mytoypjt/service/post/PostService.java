package org.mytoypjt.service.post;

import org.mytoypjt.dao.*;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.entity.Reply;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.mytoypjt.service.post.strategy.pagecount.PageCountStrategyContext;
import org.mytoypjt.service.post.strategy.posts.BasePostsStrategy;
import org.mytoypjt.service.post.strategy.posts.PostsStrategyContext;
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
    private PageCountStrategyContext pageCountStrategyContext;
    @Autowired
    private PostsStrategyContext postsStrategyContext;

    final int PICTURE_COUNT = 10;
    final int SORT_REALTIME = 1;
    final int SORT_DAYS_LIKE = 2;
    final int SORT_WEEKS_LIKE = 3;

    final int POST_COUNT_IN_PAGE = 12;
    final int PAGE_COUNT_IN_PAGE = 5;

    public PostService(){
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
        PostsOptionVO options = new PostsOptionVO(pageNo, sortType);
        options.setPostCountInPage(POST_COUNT_IN_PAGE);
        options.setPageCount(PAGE_COUNT_IN_PAGE);

        int pageTotalCount = pageCountStrategyContext.getStrategy(type).getPageCount(options);

        options.setStartEndPageNo(pageTotalCount, 5);

        return options;
    }

    public PostsOptionVO getDefaultPostsOption() {
        int pageTotalCount = postDao.getTotalPostCount();
        PostsOptionVO options = new PostsOptionVO("1", "1");
        options.setStartEndPageNo(pageTotalCount, PAGE_COUNT_IN_PAGE);
        return options;
    }

    public List<Post> getPosts(PostsOptionVO options, Map<String, String> paramMap){
        PostSortType sortType = getPostSortType(options.getSortType());
        BasePostsStrategy strategy = postsStrategyContext.getInstance(sortType);
        strategy.setPostCountInPage(POST_COUNT_IN_PAGE);

        List<Post> posts = strategy.getPosts(options, paramMap);
        posts.forEach(post -> {
            if (post.getTitle().length() > 15)
                post.setTitle(post.getTitle().substring(0,11) + "...");
        });
        return posts;
    }

    public Post getPost(int postNo) {
        return postDao.getPost(postNo);
    }

    public void createPost(Post post) {
        String nicname = post.getnameAnonymous() ? "누군가" : post.getNicname();
        post.setNicname(nicname);

        String city = post.getcityAnonymous() ? "어딘가" : post.getCity();
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

    public void refreshCommentCountOfAllPost(){
        List<Integer> allPostNoList = postDao.getPostNoListOfAllPost();
        allPostNoList.forEach(postNo -> {
            int commentCount = commentDao.getCommentCount(postNo);
            postDao.saveCommentCount(postNo, commentCount);
        });

    }

    public List<Comment> getComments(int postNo) {
        return commentDao.getComments(postNo);
    }

    public void createComment(String no, Profile profile, String nameAnonymous, String content) {
        if (isNull(no, profile, content))
            return;

        if (nameAnonymous == null)
            nameAnonymous = "off";

        boolean isAnonymous = false;
        if (nameAnonymous.equals("on"))
            isAnonymous = true;

        int postNo = Integer.parseInt(no);
        String nicname = isAnonymous ? "누군가" : profile.getNicname();

        Comment comment = new Comment(content, postNo, nicname, isAnonymous);

        try {
            commentDao.createComment(comment);

            int commentCount = commentDao.getCommentCount(postNo);
            postDao.updateCommentCount(postNo, commentCount);
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
