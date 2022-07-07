package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.PropertiesControllerTemplete;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.models.entity.User;
import org.mytoypjt.service.LoginService;

import javax.servlet.http.HttpSession;

public class LoginController extends PropertiesControllerTemplete {

    @Override
    public Object doGet() {
        if (isUserExist())
            return "main";

        return "login";
    }

    @Override
    public Object doPost() {
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

//        Cookie cookie = new Cookie("behindUserSessionId", httpSession.getId());
//        resp.addCookie(cookie);

        return viewInfo;
    }
}
