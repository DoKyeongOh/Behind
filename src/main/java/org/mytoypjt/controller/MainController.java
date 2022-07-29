package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;
import org.mytoypjt.service.post.PostService;

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
