package org.mytoypjt.controller;

import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.entity.Reply;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    PostService postService;

    final String REPLIES = "replies";
    final String COMMENT = "comment";
    final String PARENT_TITLE = "parentPost";

    public CommentController() {
        int a = 32;
    }

    @PostMapping(path = "/comment")
    public ModelAndView entryComment(@RequestParam Map<String, String> param, HttpSession session){

        String nameAnonymous = param.get("nameAnonymous");
        String postNo = param.get("postNo");
        String accountNo = param.get("accountNo");
        String content = param.get("content");

        Profile profile = (Profile) session.getAttribute("profile");

        try {
            postService.createComment(postNo, profile, nameAnonymous, content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView(new RedirectView("/post?no="+postNo));
    }

    @GetMapping(path = "/comment")
    public ModelAndView showCommentPage(@RequestParam Map<String, String> param){
        String no = param.get("no");

        ModelAndView mv = new ModelAndView();

        Comment comment = postService.getComment(no);
        if (Comment.isCorrectComment(comment)) {
            mv.setView(new RedirectView("/post?no=" + no));
            return mv;
        }

        Post post = postService.getPost(comment.getPostNo());
        String title = post.getTitle();
        if (title.length() > 7)
            title = title.substring(0,7) + "...";

        List<Reply> replies = postService.getReplies(comment);
        mv.addObject(COMMENT, comment);
        mv.addObject(REPLIES, replies);
        mv.addObject(PARENT_TITLE, title);
        mv.setViewName("commentDetailPage");

        return mv;
    }

    @PostMapping(path = "/reply")
    public ViewInfo createReply(@RequestParam Map<String, String> param){
        String content = param.get("content");
        String replierNo = param.get("accountNo");
        String commentNo = param.get("commentNo");
        String isAnonName = param.get("nameAnonymous");

        try {
            postService.createReply(content, replierNo, commentNo, isAnonName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ViewInfo.getRedirectViewInfo("/comment?no="+commentNo);
    }

}
