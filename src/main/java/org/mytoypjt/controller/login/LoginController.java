package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.models.entity.User;
import org.mytoypjt.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends PropertiesControllerTemplete {

    @RequestMapping(uri = "/login", method = "GET")
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping(uri = "/login", method = "POST")
    public ViewInfo getLoginSession(HttpServletRequest req, HttpServletResponse resp){
        int a = 3;
        return login(req, resp);
    }

    @Override
    public Object doGet() {
        if (isUserExist())
            return "main";

        return "login";
    }

    @Override
    public Object doPost() {
        return login(req, resp);
    }

    public ViewInfo login(HttpServletRequest req, HttpServletResponse resp){
        String userId = req.getParameter("userId");
        String userPw = req.getParameter("userPw");

        LoginService loginService = new LoginService();
        User user = loginService.getUser(userId, userPw);

        ViewInfo viewInfo = new ViewInfo("main");
        if (user == null) {
            viewInfo.setViewName("login");
            return viewInfo;
        }

        viewInfo.setRedirectRequired();
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user", user);

        return viewInfo;
    }
}
