package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexController extends PropertiesControllerTemplete {

    @Override
    public Object doGet() {
        return "index";
    }

    @RequestMapping(uri = "/", method = "POST")
    @Override
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
}

