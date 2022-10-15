package org.mytoypjt.service.post;

import org.mytoypjt.dao.*;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.*;
import org.mytoypjt.models.vo.PostOption;
import org.mytoypjt.service.post.strategy.pagecount.PostCountStrategyContext;
import org.mytoypjt.service.post.strategy.posts.PostsStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private LikeDao likeDao;
    @Autowired
    private PostLogDao postLogDao;
    @Autowired
    private CommentLogDao commentLogDao;
    @Autowired
    private ReplyLogDao replyLogDao;
    @Autowired
    private LikeLogDao likeLogDao;
    @Autowired
    private PostCountStrategyContext postCountStrategyContext;
    @Autowired
    private PostsStrategyContext postsStrategyContext;

    public PostService(){
    }

    public PostOption createPostsOption(PostOption reqOp, PostOption sessOp){
        String sortType = reqOp.isExistSortType() ?
                reqOp.getSortType() : (sessOp.isExistSortType() ? sessOp.getSortType() : "1");

        String pageNo = reqOp.isExistPageNo() ?
                reqOp.getPageNo() : (sessOp.isExistPageNo() ? sessOp.getPageNo() : "1");

        PostSortType type = getPostSortType(sortType);
        PostOption options = new PostOption(pageNo, sortType);
        options.setPostCountLimitInMainPage();

        String searchWord = reqOp.getOptionMap().get(PostConst.SEARCH_WORD);
        if (searchWord == null)
            searchWord = sessOp.getOptionMap().get(PostConst.SEARCH_WORD);
        options.getOptionMap().put(PostConst.SEARCH_WORD, searchWord);

        int postCount = postCountStrategyContext.getStrategy(type).getPostCount(options);
        options.setStartEndPageNo(postCount);

        return options;
    }

    public PostOption getDefaultPostsOption() {
        int pageTotalCount = postDao.getTotalPostCount();
        PostOption options = new PostOption("1", "1");
        options.setPostCountLimitInMainPage();
        options.setStartEndPageNo(pageTotalCount);
        return options;
    }

    public List<Post> getPosts(PostOption options){
        PostSortType sortType = getPostSortType(options.getSortType());

        List<Post> posts =
                postsStrategyContext.getStrategy(sortType).getPosts(options);

        posts.forEach(post -> {
            if (post.getTitle().length() > 15)
                post.setTitle(post.getTitle().substring(0,11) + "...");
        });
        return posts;
    }

    public Post getPost(int postNo) {
        return postDao.getPost(postNo);
    }

    @Transactional
    public void createPost(Post post) throws Exception{
        String nicname = post.getnameAnonymous() ? "누군가" : post.getNicname();
        post.setNicname(nicname);

        String city = post.getcityAnonymous() ? "어딘가" : post.getCity();
        post.setCity(city);

        postDao.createPost(post);
        postLogDao.writeLog(post, "게시");
    }

    @Transactional
    public void updatePost(Post post) throws Exception {
        postDao.updatePost(post);
        postLogDao.writeLog(post, "수정");
    }

    @Transactional
    public void deletePost(int postNo) throws Exception {
        Post post = postDao.getPost(postNo);
        if (!Post.isCorrectPost(post))
            return;
        postDao.deletePost(postNo);
        postLogDao.writeLog(post, "삭제");
    }

    @Transactional
    public void toggleLike(String post, String account) throws Exception {
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

    @Transactional
    public void refreshCommentCountOfAllPost() throws Exception {
        List<Integer> allPostNoList = postDao.getPostNoListOfAllPost();
        allPostNoList.forEach(postNo -> {
            int commentCount = commentDao.getCommentCount(postNo);
            postDao.saveCommentCount(postNo, commentCount);
        });

    }

    public List<Comment> getComments(int postNo) {
        return commentDao.getComments(postNo);
    }

    @Transactional
    public void createComment(String no, Profile profile, String nameAnonymous, String content) throws Exception {
        if (isNull(no, profile, content))
            return;

        if (nameAnonymous == null)
            nameAnonymous = "off";

        boolean isAnonymous = false;
        if (nameAnonymous.equals("on"))
            isAnonymous = true;

        int postNo = Integer.parseInt(no);
        String nicname = isAnonymous ? "누군가" : profile.getNicname();

        Comment comment = new Comment(content, profile.getAccountNo(), postNo, nicname, isAnonymous);

        commentDao.createComment(comment);
        commentLogDao.writeLog(comment, "생성");

        int commentCount = commentDao.getCommentCount(postNo);
        postDao.updateCommentCount(postNo, commentCount);
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

    @Transactional
    public void createReply(String content, Profile profile, int commentNo, String isAnonName) throws Exception {
        boolean isAnonymousName = true;
        if (isAnonName == null)
            isAnonymousName = false;

        Reply reply = new Reply(content, profile.getAccountNo(), commentNo, isAnonymousName, profile.getNicname());

        replyDao.createReply(reply);
        replyLogDao.writeLog(reply, "생성");
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
            case "4": return PostSortType.SEARCH_BY_TITLE;
            case "5": return PostSortType.SEARCH_BY_CONTENT;
            case "6": return PostSortType.SEARCH_BY_NICNAME;
            case "7": return PostSortType.HASH_TAG;
            default: return PostSortType.REAL_TIME;
        }
    }

}
