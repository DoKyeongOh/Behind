package org.mytoypjt.controller;

import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.entity.Reply;
import org.mytoypjt.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

    @DeleteMapping(path = "/comment")
    public ModelAndView deleteComment(@RequestParam("commentNo") int commentNo,
                                      @RequestParam("postNo") int postNo) {
        try {
            postService.deleteComment(commentNo);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(new RedirectView("/post?no="+postNo));
    }

    @PostMapping(path = "/comment")
    public ModelAndView createComment(@RequestParam Map<String, String> param,
                                      @SessionAttribute(name = "profile")Profile profile){

        String nameAnonymous = param.get("nameAnonymous");
        String postNo = param.get("postNo");
        String accountNo = param.get("accountNo");
        String content = param.get("content");

        try {
            postService.createComment(postNo, profile, nameAnonymous, content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView(new RedirectView("/post?no="+postNo));
    }

    @PutMapping(path = "/comment")
    public ModelAndView updateComment(@RequestParam Map<String, String> param,
                                      @SessionAttribute("profile") Profile profile) {
        String content = param.get("content");
        String commentNo = param.get("commentNo");
        int postNo = Integer.parseInt(param.get("pageNo"));

        boolean nameAnonymous = param.get("nameAnonymous").equals("true") ? true : false;
        String nicname = nameAnonymous ? "누군가" : profile.getNicname();

        Comment comment = new Comment(content, profile.getAccountNo(), postNo, nicname, nameAnonymous);
        ModelAndView mv = new ModelAndView(new RedirectView("/comment?no="+commentNo));
        try {
            postService.updateComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    @GetMapping(path = "/comment/page/{pageNo}")
    public ModelAndView getCommentPage(@PathVariable(name = "pageNo") int pageNo,
                                       @RequestParam Map<String, String> param) {
        ModelAndView mv = new ModelAndView();
        switch (pageNo) {
            case 1: {
                String commentNo = param.get("commentNo");
                Comment comment = postService.getComment(commentNo);
                mv.addObject("comment", comment);
                mv.setViewName("commentModifyPage");
                return mv;
            }
        }

        mv.setView(new RedirectView("/main/page"));
        return mv;
    }


    @PostMapping(path = "/reply")
    public ModelAndView createReply(@RequestParam Map<String, String> param,
                                @SessionAttribute(name = "profile") Profile profile){
        String content = param.get("content");
        String commentNo = param.get("commentNo");
        String isAnonName = param.get("nameAnonymous");

        try {
            postService.createReply(content, profile, Integer.parseInt(commentNo), isAnonName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(new RedirectView("/comment?no="+commentNo));
    }

}
