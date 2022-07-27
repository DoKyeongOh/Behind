package org.mytoypjt.controller.post;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.entity.Reply;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CommentController {

    final String REPLIES = "replies";
    final String COMMENT = "comment";

    PostService postService;

    public CommentController() {
        postService = new PostService();
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

    @RequestMapping(uri = "/comment", method = "get")
    public ViewInfo showCommentPage(HttpServletRequest req, HttpServletResponse resp){
        String no = req.getParameter("no");

        Comment comment = postService.getComment(no);
        if (Comment.isCorrectComment(comment))
            return ViewInfo.getRedirectViewInfo("/post?no="+no);

        List<Reply> replies = postService.getReplies(comment);
        req.setAttribute(COMMENT, comment);
        req.setAttribute(REPLIES, replies);

        return new ViewInfo("commentDetailPage");
    }

}
