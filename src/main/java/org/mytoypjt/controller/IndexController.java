package org.mytoypjt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends ControllerTemplete{

    @Override
    public String executeGetRequest(HttpServletRequest req, HttpServletResponse resp) {
        return "index";
    }

    @Override
    public String executePostRequest(HttpServletRequest req, HttpServletResponse resp) {
        return "index";
    }
}
