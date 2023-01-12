package org.mytoypjt.controller;

import org.mytoypjt.consts.PostConst;
import org.mytoypjt.consts.SessionConst;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.vo.PostOption;
import org.mytoypjt.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    public PostController (){}

    @GetMapping(path = "/main/page")
    public ModelAndView showMainPage(@RequestParam Map<String, String> param, HttpSession session){
        ModelAndView mv = new ModelAndView("mainPage");

        try {
            postService.refreshCommentCountOfAllPost();
        } catch (Exception e) {
            e.printStackTrace();
        }

        PostOption postOption = postService.getDefaultPostsOption();
        List<Post> posts = postService.getPosts(postOption);
        mv.addObject("posts", posts);

        mv.addObject("realtimeChecked", "checked");
        mv.addObject("daysChecked", "");
        mv.addObject("weeksChecked", "");

        session.setAttribute(SessionConst.POST_OPTION_KEY, postOption);

        return mv;
    }

    @GetMapping(path = "/posts")
    public ModelAndView showPosts(HttpSession session, @RequestParam Map<String, String> param){
        PostOption optionInSession = (PostOption) session.getAttribute(SessionConst.POST_OPTION_KEY);
        PostOption optionInRequest = new PostOption(param.get("pageNo"), param.get("type"));

        optionInRequest.getOptionMap().putAll(param);
        PostOption actualOption = postService.createPostsOption(optionInRequest, optionInSession);

        List<Post> posts = postService.getPosts(actualOption);
        session.setAttribute(SessionConst.POST_OPTION_KEY, actualOption);

        ModelAndView mv = new ModelAndView("mainPage");
        mv.addObject("posts", posts);

        PostSortType postSortType = postService.getPostSortType(actualOption.getSortType());
        switch (postSortType) {
            case REAL_TIME: {
                mv.addObject("realtimeChecked", "checked");
                break;
            }

            case DAYS_FAVORITE: {
                mv.addObject("daysChecked", "checked");
                break;
            }

            case WEEKS_FAVORITE: {
                mv.addObject("weeksChecked", "checked");
                break;
            }
        }

        return mv;
    }

    @GetMapping(path = "/post")
    public ModelAndView showPost(HttpSession session, @RequestParam Map<String, Object> param){
        String no = (String) param.get("no");
        Post post = postService.getPost(Integer.parseInt(no));

        if (post == null)
            return new ModelAndView(new RedirectView("/main/page"));

        List<Comment> comments = postService.getComments(post.getPostNo());

        ModelAndView mv = new ModelAndView("postDetailPage");
        mv.addObject(PostConst.POSTER_CITY, post.getCity());
        mv.addObject(PostConst.POST, post);
        mv.addObject(PostConst.COMMENTS, comments);

        Profile profile = (Profile) session.getAttribute(SessionConst.USER_PROFILE);

        int accountNo = profile.getAccountNo();
        boolean isLike = postService.isLikePost(no, Integer.toString(accountNo));
        mv.addObject(PostConst.IS_LIKE, isLike);

        return mv;
    }

    @PostMapping(path = "/post")
    public ModelAndView createPost(@SessionAttribute(name = "profile")Profile profile,
                                   @RequestParam Map<String, String> param){

        String title = param.get("title");
        String content = param.get("content");
        int pictureNo = Integer.parseInt(param.get("imgNo"));
        boolean isAnonymousName = (Objects.equals(param.get("isAnonName"), "true")) ? true : false;
        boolean isAnonymousCity = (Objects.equals(param.get("isAnonCity"), "true")) ? true : false;
        int commentCount = 0;
        int likeCount = 0;

        Post post = new Post(
                title, content, new Date(), commentCount, likeCount, profile.getAccountNo(),
                pictureNo, isAnonymousName, isAnonymousCity, profile.getNickname(), profile.getCity());

        try {
            postService.createPost(post);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView(new RedirectView("/post/page/2"));
    }

    @GetMapping(path = "/post/page/{pageNo}")
    public String showPostCreatePage(@PathVariable(value = "pageNo")int pageNo,
                                     @RequestParam Map<String, String> param, Model model){
        switch (pageNo) {
            case 1 : return PostConst.POST_CREATE_PAGE;
            case 2 : return PostConst.POST_CREATE_COMPLETE_PAGE;
            case 3 : {
                int postNo = Integer.parseInt(param.get("postNo"));
                Post post = postService.getPost(postNo);
                if (post == null) return "mainPage";

                model.addAttribute("post", post);
                return PostConst.POST_MODIFY_PAGE;
            }
            default: return PostConst.POST_CREATE_PAGE;
        }
    }

    @DeleteMapping(path = "/post")
    public ModelAndView deletePost(@RequestParam(name = "postNo")int postNo){
        try {
            postService.deletePost(postNo);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(new RedirectView("/main/page"));
    }

    @PutMapping(path = "/post")
    public ModelAndView modifyPost(@SessionAttribute(name = "profile")Profile profile,
                               @RequestParam Map<String, String> param){

        int postNo = Integer.parseInt(param.get("postNo"));
        String title = param.get("title");
        String content = param.get("content");
        int pictureNo = Integer.parseInt(param.get("imgNo"));
        boolean isAnonymousName = (Objects.equals(param.get("isAnonName"), "true")) ? true : false;
        boolean isAnonymousCity = (Objects.equals(param.get("isAnonCity"), "true")) ? true : false;
        String nickname = isAnonymousName ? "누군가" : profile.getNickname();
        String city = isAnonymousCity ? "어딘가" : profile.getCity();

        Post post = new Post(postNo, title, content, new Date(), 0, 0,
                profile.getAccountNo(), pictureNo, isAnonymousName, isAnonymousCity, nickname, city);

        try {
            postService.updatePost(post);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView(new RedirectView("/post?no="+post.getPostNo()));
    }

    @PostMapping(path = "/like")
    public ModelAndView togglePostLike(@RequestParam Map<String, String> param, Model model){
        String postNo = param.get("postNo");
        String accountNo = param.get("accountNo");

        try {
            postService.toggleLike(postNo, accountNo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isLike = postService.isLikePost(postNo, accountNo);
        model.addAttribute("isLike", isLike);

        Post post = postService.getPost(Integer.parseInt(postNo));
        if (post == null)
            return new ModelAndView(new RedirectView("/posts?type=1"));
        else
            return new ModelAndView(new RedirectView("/post?no="+post.getPostNo()));
    }


}
