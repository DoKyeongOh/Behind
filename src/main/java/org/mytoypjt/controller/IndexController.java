package org.mytoypjt.controller;

import org.json.simple.JSONObject;
import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;
import org.mytoypjt.models.etc.ViewInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexController extends PropertiesControllerTemplete {

    @RequestMapping(uri = "/", method = "POST")
    public Object doPost() {
        return "index";
    }

    @RequestMapping(uri = "/", method = "GET")
    public String showIndexPage(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null)
            return "mainPage";

        return "index";
    }

    @RequestMapping(uri = "/idSameTest", method = "post")
    public ViewInfo test(){
        int a = 23;
        return new ViewInfo("");
    }
}

