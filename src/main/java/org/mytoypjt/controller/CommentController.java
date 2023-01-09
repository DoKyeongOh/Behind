package org.mytoypjt.controller;

import org.mytoypjt.consts.PostConst;
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

        List<Reply> replies = postService.getReplies(comment.getCommentNo());
        mv.addObject(PostConst.COMMENT, comment);
        mv.addObject(PostConst.REPLIES, replies);
        mv.addObject(PostConst.POST_TITLE, title);
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
        int commentNo = Integer.parseInt(param.get("commentNo"));
        int postNo = Integer.parseInt(param.get("postNo"));

        boolean nameAnonymous = param.get("nameAnonymous") == null ? false : true;
        String nicname = nameAnonymous ? "누군가" : profile.getNickname();

        Comment comment = new Comment(content, profile.getAccountNo(), postNo, nicname, nameAnonymous);
        comment.setCommentNo(commentNo);
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

                List<Reply> replies = postService.getReplies(comment.getCommentNo());
                mv.addObject(PostConst.POST_TITLE, "게시 글");
                mv.addObject(PostConst.REPLIES, replies);
                mv.addObject(PostConst.COMMENT, comment);
                mv.setViewName("commentModifyPage");
                return mv;
            }
        }

        mv.setView(new RedirectView("/main/page"));
        return mv;
    }

    @GetMapping(path = "/reply/page/{pageNo}")
    public ModelAndView getReplyPage(@RequestParam("replyNo")int replyNo,
                                     @PathVariable("pageNo")int pageNo) {
        ModelAndView mv = new ModelAndView();
        switch (pageNo) {
            case 1: {
                try {
                    List<Reply> replies = postService.getRepliesByReplyNo(replyNo);
                    Comment comment = postService.getCommentByReplyNo(replyNo);
                    mv.addObject(PostConst.POST_TITLE, "게시 글");
                    mv.addObject(PostConst.REPLIES, replies);
                    mv.addObject(PostConst.COMMENT, comment);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mv.addObject("replyNo", replyNo);
                mv.setViewName("replyModifyPage");
                return mv;
            }
        }
        return mv;
    }

    @GetMapping(path = "/reply")
    public ModelAndView getReply(@RequestParam("no") int replyNo){
        try {
            Comment comment = postService.getCommentByReplyNo(replyNo);
            return new ModelAndView(new RedirectView("/comment?no="+comment.getCommentNo()));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(new RedirectView("/main/page"));
    }


    @PostMapping(path = "/reply")
    public ModelAndView createReply(@RequestParam Map<String, String> param,
                                @SessionAttribute(name = "profile") Profile profile){
        String content = param.get("content");
        String commentNo = param.get("commentNo");
        String nameAnonymous = param.get("nameAnonymous");

        try {
            postService.createReply(content, profile, Integer.parseInt(commentNo), nameAnonymous);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(new RedirectView("/comment?no="+commentNo));
    }

    @PutMapping(path = "/reply")
    public ModelAndView updateReply(@RequestParam Map<String, String> param) {
        String content = param.get("content");
        int replyNo = Integer.parseInt(param.get("replyNo"));
        int commentNo = Integer.parseInt(param.get("commentNo"));

        try {
            postService.updateReply(replyNo, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(new RedirectView("/comment?no="+commentNo));
    }

    @DeleteMapping(path = "/reply")
    public ModelAndView deleteReply(@RequestParam("replyNo") int replyNo,
                                    @RequestParam("commentNo") int commentNo) {
        try {
            postService.deleteReply(replyNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(new RedirectView("/comment?no="+commentNo));
    }

}
