package org.mytoypjt.controller.post;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.models.dto.PostsOptionDto;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.service.PostService;
import org.mytoypjt.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PostController {

    PostService postService;

    public PostController (){
        postService = new PostService();
    }

    @RequestMapping(uri = "/main/page", method = "get")
    public String showMainPage(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        if (session.getAttribute("profile") == null)
            return "index";

        PostsOptionDto postsOptionDto = new PostsOptionDto("1", "1");

        List<Post> posts = postService.getPosts(postsOptionDto);
        req.setAttribute("posts", posts);

        req.setAttribute("realtimeChecked", "checked");
        req.setAttribute("daysChecked", "");
        req.setAttribute("weeksChecked", "");

        session.setAttribute("postsOption", postsOptionDto);

        return "mainPage";
    }
    @RequestMapping(uri = "/posts", method = "get")
    public String showPosts(HttpServletRequest req, HttpServletResponse resp){
        if (!ControllerUtils.isExistProfileSession(req))
            return "index";

        HttpSession session = req.getSession();

        PostsOptionDto optionInSession =
                (PostsOptionDto) session.getAttribute("postsOption");

        PostsOptionDto optionInRequest = new PostsOptionDto(
                req.getParameter("pageNo"),
                req.getParameter("type")
        );

        PostsOptionDto actualOption = postService.getPostsOption(optionInRequest, optionInSession);

        List<Post> posts = postService.getPosts(actualOption);
        int pageCount = postService.getPageCount();

        req.setAttribute("posts", posts);
        req.setAttribute("pageCount", pageCount);

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
    public String showPost(HttpServletRequest req, HttpServletResponse resp){

        String no = req.getParameter("no");
        Post post = postService.getPost(no);

        if (post == null)
            return "postDetailPage";

        List<Comment> comments = postService.getComments(post.getPostNo());
        req.setAttribute("post", post);
        req.setAttribute("comments", comments);

        HttpSession session = req.getSession();
        Profile profile = (Profile) session.getAttribute("profile");

        int accountNo = profile.getAccountNo();

        boolean isLike = postService.isLikePost(no, accountNo);
        req.setAttribute("isLike", isLike);

        return "postDetailPage";
    }

    @RequestMapping(uri = "/post/page", method = "get")
    public String showPostPage(){
        return "postCreatePage";
    }

    @RequestMapping(uri = "/post", method = "post")
    public ViewInfo createPost(){
        return null;
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

    @RequestMapping(uri = "/comment", method = "post")
    public ViewInfo entryComment(HttpServletRequest req, HttpServletResponse resp){

        String isUseAnonymousName = req.getParameter("isUseAnonymousName");
        String postNo = req.getParameter("postNo");
        String accountNo = req.getParameter("accountNo");
        String content = req.getParameter("content");

        HttpSession session = req.getSession();
        Profile profile = (Profile) session.getAttribute("profile");

        postService.createComment(postNo, profile, isUseAnonymousName, content);
        Post post = postService.getPost(postNo);

        return ViewInfo.getRedirectViewInfo("/post?no="+post.getPostNo());
    }

}
