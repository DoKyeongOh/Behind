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

    PostService postService;

    public MainController (){
        postService = new PostService();
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
