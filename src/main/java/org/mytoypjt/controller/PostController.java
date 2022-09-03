package org.mytoypjt.controller;

import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.vo.PostsOptionVO;
import org.mytoypjt.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class PostController {

    @Autowired
    PostService postService;
    String postsOptionKey = "PostsOption";

    public PostController (){}

    @GetMapping(path = "/main/page")
    public ModelAndView showMainPage(@RequestParam Map<String, String> param, HttpSession session){
        ModelAndView mv = new ModelAndView("mainPage");

        PostsOptionVO postsOptionVO = postService.getDefaultPostsOption();
        List<Post> posts = postService.getPosts(postsOptionVO, param);
        List<String> cities = postService.getPostersCity(posts);

        mv.addObject("posts", posts);
        mv.addObject("cities", cities);

        mv.addObject("realtimeChecked", "checked");
        mv.addObject("daysChecked", "");
        mv.addObject("weeksChecked", "");

        session.setAttribute(this.postsOptionKey, postsOptionVO);

        return mv;
    }

    @GetMapping(path = "/posts")
    public ModelAndView showPosts(HttpSession session, @RequestParam Map<String, String> param){
        PostsOptionVO optionInSession =
                (PostsOptionVO) session.getAttribute(this.postsOptionKey);

        PostsOptionVO optionInRequest = new PostsOptionVO(
                param.get("pageNo"),
                param.get("type")
        );
        
        PostsOptionVO actualOption = postService.createPostsOption(optionInRequest, optionInSession);
        session.setAttribute(this.postsOptionKey, actualOption);

        List<Post> posts = postService.getPosts(actualOption, param);
        List<String> cities = postService.getPostersCity(posts);

        ModelAndView mv = new ModelAndView("mainPage");
        mv.addObject("posts", posts);
        mv.addObject("cities", cities);

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
        mv.addObject("posterCity", post.getCity());
        mv.addObject("post", post);
        mv.addObject("comments", comments);

        Profile profile = (Profile) session.getAttribute("profile");

        int accountNo = profile.getAccountNo();
        boolean isLike = postService.isLikePost(no, Integer.toString(accountNo));
        mv.addObject("isLike", isLike);

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
                -1, title, content, new Date(), commentCount, likeCount, profile.getAccountNo(),
                pictureNo, isAnonymousName, isAnonymousCity, profile.getNicname(), profile.getCity());
        
        postService.createPost(post);

        return new ModelAndView(new RedirectView("/post/page/2"));
    }

    @GetMapping(path = "/post/page/{pageNo}")
    public String showPostCreatePage(@PathVariable(value = "pageNo")int pageNo,
                                     @RequestParam Map<String, String> param, Model model){
        switch (pageNo) {
            case 1 : return "postCreatePage";
            case 2 : return "postCreateComplete";
            case 3 : {
                int postNo = Integer.parseInt(param.get("postNo"));
                Post post = postService.getPost(postNo);
                if (post == null) return "mainPage";

                model.addAttribute("post", post);
                return "postModifyPage";
            }
            default: return "postCreatePage";
        }
    }

    @DeleteMapping(path = "/post")
    public String deletePost(){
        return null;
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
        String nicname = isAnonymousName ? "누군가" : profile.getNicname();
        String city = isAnonymousCity ? "어딘가" : profile.getCity();

        Post post = new Post(
                postNo, title, content, new Date(), 0, 0, profile.getAccountNo(),
                pictureNo, isAnonymousName, isAnonymousCity, nicname, city);

        postService.updatePost(post);

        return new ModelAndView(new RedirectView("/post?no="+post.getPostNo()));
    }

    @PostMapping(path = "/like")
    public ModelAndView togglePostLike(@RequestParam Map<String, String> param, Model model){
        String postNo = param.get("postNo");
        String accountNo = param.get("accountNo");

        postService.toggleLike(postNo, accountNo);

        boolean isLike = postService.isLikePost(postNo, accountNo);
        model.addAttribute("isLike", isLike);

        Post post = postService.getPost(Integer.parseInt(postNo));
        if (post == null)
            return new ModelAndView(new RedirectView("/posts?type=1"));
        else
            return new ModelAndView(new RedirectView("/post?no="+post.getPostNo()));
    }


}
