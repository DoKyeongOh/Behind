package org.mytoypjt.controller.post;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.service.post.PostService;
import org.mytoypjt.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PostController {

    PostService postService;
    String postsOptionKey = "PostsOption";

    public PostController (){
        postService = new PostService();
    }

    @RequestMapping(uri = "/main/page", method = "get")
    public String showMainPage(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        if (session.getAttribute("profile") == null)
            return "index";

        PostsOptionVO postsOptionVO = postService.getDefaultPostsOption();

        List<Post> posts = postService.getPosts(postsOptionVO, req.getParameterMap());
        List<String> cities = postService.getPostersCity(posts);
        req.setAttribute("posts", posts);
        req.setAttribute("cities", cities);

        req.setAttribute("realtimeChecked", "checked");
        req.setAttribute("daysChecked", "");
        req.setAttribute("weeksChecked", "");

        session.setAttribute(this.postsOptionKey, postsOptionVO);

        return "mainPage";
    }

    @RequestMapping(uri = "/posts", method = "get")
    public String showPosts(HttpServletRequest req, HttpServletResponse resp){
        if (!ControllerUtils.isExistProfileSession(req))
            return "index";

        HttpSession session = req.getSession();

        PostsOptionVO optionInSession =
                (PostsOptionVO) session.getAttribute(this.postsOptionKey);

        PostsOptionVO optionInRequest = new PostsOptionVO(
                req.getParameter("pageNo"),
                req.getParameter("type")
        );
        
        PostsOptionVO actualOption = postService.createPostsOption(optionInRequest, optionInSession);
        session.setAttribute(this.postsOptionKey, actualOption);

        List<Post> posts = postService.getPosts(actualOption, req.getParameterMap());
        List<String> cities = postService.getPostersCity(posts);
        req.setAttribute("posts", posts);
        req.setAttribute("cities", cities);

        PostSortType postSortType = postService.getPostSortType(actualOption.getSortType());
        switch (postSortType) {
            case REAL_TIME: {
                req.setAttribute("realtimeChecked", "checked");
                break;
            }

            case DAYS_FAVORITE: {
                req.setAttribute("daysChecked", "checked");
                break;
            }

            case WEEKS_FAVORITE: {
                req.setAttribute("weeksChecked", "checked");
                break;
            }
        }

        return "mainPage";
    }

    @RequestMapping(uri = "/post", method = "get")
    public ViewInfo showPost(HttpServletRequest req, HttpServletResponse resp){
        String no = req.getParameter("no");
        
        Post post = postService.getPost(no);

        if (post == null)
            return ViewInfo.getRedirectViewInfo("/main/page");

        Profile posterProfile = postService.getPosterProfile(post.getAccountNo());
        List<Comment> comments = postService.getComments(post.getPostNo());

        req.setAttribute("posterCity", post.getCity());
        req.setAttribute("post", post);
        req.setAttribute("comments", comments);

        HttpSession session = req.getSession();
        Profile profile = (Profile) session.getAttribute("profile");

        int accountNo = profile.getAccountNo();

        boolean isLike = postService.isLikePost(no, Integer.toString(accountNo));
        req.setAttribute("isLike", isLike);

        return new ViewInfo("postDetailPage");
    }

    @RequestMapping(uri = "/post/page/1", method = "get")
    public String showPostPage(){
        return "postCreatePage";
    }

    @RequestMapping(uri = "/post", method = "post")
    public ViewInfo createPost(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        Profile profile = (Profile) session.getAttribute("profile");

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int pictureNo = Integer.parseInt(req.getParameter("imgNo"));
        boolean isAnonymousName = (Objects.equals(req.getParameter("isAnonName"), "true")) ? true : false;
        boolean isAnonymousCity = (Objects.equals(req.getParameter("isAnonCity"), "true")) ? true : false;
        int commentCount = 0;
        int likeCount = 0;

        Post post = new Post(
                -1,
                title,
                content,
                new Date(),
                commentCount,
                likeCount,
                profile.getAccountNo(),
                pictureNo,
                isAnonymousName,
                isAnonymousCity,
                profile.getNicname(),
                profile.getCity());
        
        postService.createPost(profile, post);

        return ViewInfo.getRedirectViewInfo("/post/page/2");
    }

    @RequestMapping(uri = "/post/page/2", method = "get")
    public String showCreatePostCompletePage(){
        int a = 3;
        return "postCreateComplete";
    }

    @RequestMapping(uri = "/post/page/3", method = "get")
    public String showPostModifyPage(HttpServletRequest req, HttpServletResponse resp){
        String no = req.getParameter("postNo");
        Post post = postService.getPost(no);
        if (post == null) return "mainPage";

        req.setAttribute("post", post);

        return "postModifyPage";
    }

    @RequestMapping(uri = "/post", method = "delete")
    public ViewInfo deletePost(){
        return null;
    }

    @RequestMapping(uri = "/post", method = "put")
    public ViewInfo modifyPost(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        Profile profile = (Profile) session.getAttribute("profile");

        int postNo = Integer.parseInt(req.getParameter("postNo"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int pictureNo = Integer.parseInt(req.getParameter("imgNo"));
        boolean isAnonymousName = (Objects.equals(req.getParameter("isAnonName"), "true")) ? true : false;
        boolean isAnonymousCity = (Objects.equals(req.getParameter("isAnonCity"), "true")) ? true : false;

        Post post = new Post(
                postNo, title, content, new Date(), 0, 0, profile.getAccountNo(),
                pictureNo, isAnonymousName, isAnonymousCity, profile.getNicname(), profile.getCity());

        postService.updatePost(post);

        return ViewInfo.getRedirectViewInfo("/post?no="+post.getPostNo());
    }

    @RequestMapping(uri = "/like", method = "post")
    public ViewInfo togglePostLike(HttpServletRequest req, HttpServletResponse resp){
        String postNo = req.getParameter("postNo");
        String accountNo = req.getParameter("accountNo");

        
        postService.toggleLike(postNo, accountNo);

        boolean isLike = postService.isLikePost(postNo, accountNo);
        req.setAttribute("isLike", isLike);

        Post post = postService.getPost(postNo);
        if (post == null)
            return ViewInfo.getRedirectViewInfo("/posts?type=1");
        else
            return ViewInfo.getRedirectViewInfo("/post?no="+post.getPostNo());
    }


}
