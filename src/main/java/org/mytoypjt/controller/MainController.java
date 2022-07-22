package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MainController extends PropertiesControllerTemplete {

    PostService postViewService;

    public MainController (){
        postViewService = new PostService();
    }

    @RequestMapping(uri = "/main/page", method = "get")
    public String showMainPage(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        if (session.getAttribute("profile") == null)
            return "index";

        List<Post> postList = postViewService.getDefaultPosts(1);
        req.setAttribute("posts", postList);

        req.setAttribute("realtimeChecked", "checked");
        req.setAttribute("daysChecked", "");
        req.setAttribute("weeksChecked", "");

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
