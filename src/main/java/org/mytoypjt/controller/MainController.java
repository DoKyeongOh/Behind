package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.service.PostViewService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

public class MainController extends PropertiesControllerTemplete {

    PostViewService postViewService;

    public MainController (){
        postViewService = new PostViewService();
    }

    @RequestMapping(uri = "/main/page", method = "get")
    public String showMainPage(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        if (session.getAttribute("profile") == null)
            return "index";

        List<Post> postList = postViewService.getDefaultPosts(1);
        req.setAttribute("post", postList);

        return "mainPage";
    }

    @RequestMapping(uri = "/posts", method = "get")
    public String showPosts(HttpServletRequest req, HttpServletResponse resp){

        String pageNo = req.getParameter("pageNo");
        String sortType = req.getParameter("sortType");

        List<Post> posts = postViewService.getPosts(pageNo, sortType);
        if (posts != null)
            req.setAttribute("post", posts);

        return "mainPage";
    }

    @Override
    public Object doGet() {
        if (!isProfileExist())
            return "index";

        return "main";
    }

    @Override
    public Object doPost() {
        return "main";
    }


}
