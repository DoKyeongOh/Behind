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

    @RequestMapping(uri = "/login/page", method = "GET")
    public String getLoginPage(){
        return "loginPage";
    }

    @RequestMapping(uri = "/login", method = "POST")
    public ViewInfo getLoginSession(HttpServletRequest req, HttpServletResponse resp){
        String userId = req.getParameter("userId");
        String userPw = req.getParameter("userPw");

        LoginService loginService = new LoginService();
        User user = loginService.getUser(userId, userPw);

        if (user == null) {
            return new ViewInfo("loginPage");
        }

        ViewInfo viewInfo = new ViewInfo();
        viewInfo.setRedirectTo("/main/page");
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user", user);

        return viewInfo;
    }

    @Override
    public Object doGet() {
        if (isUserExist())
            return "main";

        return "loginPage";
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

        ViewInfo viewInfo = new ViewInfo("mainPage");
        if (user == null) {
            viewInfo.setViewName("loginPage");
            return viewInfo;
        }

        viewInfo.setRedirectRequire(true);
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user", user);

        return viewInfo;
    }
}
