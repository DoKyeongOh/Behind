package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.entity.Reply;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    PostService postService;

    final String REPLIES = "replies";
    final String COMMENT = "comment";
    final String PARENT_TITLE = "parentPost";

    public CommentController() {}

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

    @RequestMapping(uri = "/comment", method = "get")
    public ViewInfo showCommentPage(HttpServletRequest req, HttpServletResponse resp){
        String no = req.getParameter("no");

        Comment comment = postService.getComment(no);
        if (Comment.isCorrectComment(comment))
            return ViewInfo.getRedirectViewInfo("/post?no="+no);

        Post post = postService.getPost(Integer.toString(comment.getPostNo()));
        String title = post.getTitle();
        if (title.length() > 7)
            title = title.substring(0,7) + "...";

        List<Reply> replies = postService.getReplies(comment);
        req.setAttribute(COMMENT, comment);
        req.setAttribute(REPLIES, replies);
        req.setAttribute(PARENT_TITLE, title);

        return new ViewInfo("commentDetailPage");
    }

    @RequestMapping(uri = "/reply", method = "post")
    public ViewInfo createReply(HttpServletRequest req, HttpServletResponse resp){
        String content = req.getParameter("content");
        String replierNo = req.getParameter("accountNo");
        String commentNo = req.getParameter("commentNo");
        String isAnonName = req.getParameter("isUseAnonymousName");

        postService.createReply(content, replierNo, commentNo, isAnonName);
        return ViewInfo.getRedirectViewInfo("/comment?no="+commentNo);
    }

}
