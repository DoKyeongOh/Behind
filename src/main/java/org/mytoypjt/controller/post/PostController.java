package org.mytoypjt.controller.post;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.models.entity.Comment;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.service.PostViewService;
import org.mytoypjt.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PostController {

    PostViewService postViewService;

    public PostController (){
        postViewService = new PostViewService();
    }

    @RequestMapping(uri = "/posts", method = "get")
    public String showPosts(HttpServletRequest req, HttpServletResponse resp){
        if (!ControllerUtils.isExistProfileSession(req))
            return "index";

        String pageNo = req.getParameter("pageNo");
        String sortType = req.getParameter("type");

        List<Post> posts = postViewService.getPosts(pageNo, sortType);
        if (posts != null)
            req.setAttribute("posts", posts);

        switch (postViewService.getPostSortType(sortType)) {
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
        Post post = postViewService.getPost(no);

        if (post == null)
            return "postDetailPage";

        List<Comment> comments = postViewService.getComments(post.getPostNo());
        req.setAttribute("post", post);
        req.setAttribute("comments", comments);

        return "postDetailPage";
    }

}
