package org.mytoypjt.controller.post;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
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

        return "mainPage";
    }
}
