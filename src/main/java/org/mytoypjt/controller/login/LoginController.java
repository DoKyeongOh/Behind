package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.ControllerTemplete;
import org.mytoypjt.servlet.ViewInfo;
import org.mytoypjt.entity.User;
import org.mytoypjt.service.LoginService;

import javax.servlet.http.HttpSession;

public class LoginController extends ControllerTemplete {

    @Override
    public Object executeGetRequest() {
        if (isUserExist())
            return "main";
        return "login";
    }

    @Override
    public Object executePostRequest() {
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
