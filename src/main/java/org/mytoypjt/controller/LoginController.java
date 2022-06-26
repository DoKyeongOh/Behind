package org.mytoypjt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements IController {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/login.jsp";
    }
}
