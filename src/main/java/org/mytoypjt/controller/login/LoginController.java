package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.service.LoginService;
import org.mytoypjt.service.annotation.Transaction;
import org.mytoypjt.utils.ControllerUtils;
import org.mytoypjt.utils.TransactionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends PropertiesControllerTemplete {

    LoginService loginService;
    public LoginController() {
        loginService = (LoginService) TransactionManager.getInstance(LoginService.class);
    }

    @RequestMapping(uri = "/login/page", method = "GET")
    public ViewInfo getLoginPage(HttpServletRequest req, HttpServletResponse resp){
        if (ControllerUtils.isExistProfileSession(req))
            return ViewInfo.getRedirectViewInfo("/main/page");

        return new ViewInfo("loginPage");
    }

    @RequestMapping(uri = "/login", method = "POST")
    public ViewInfo getLoginSession(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("accountId");
        String pw = req.getParameter("accountPw");

        Profile profile = loginService.getProfile(id, pw);

        if (profile == null)
            return new ViewInfo("loginPage");

        ViewInfo viewInfo = new ViewInfo();
        viewInfo.setRedirectTo("/main/page");
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("profile", profile);

        return viewInfo;
    }

    @RequestMapping(uri = "/login", method = "delete")
    public ViewInfo deleteLoginSession(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        session.setAttribute("profile", null);
        return ViewInfo.getRedirectViewInfo("/");
    }

    @Override
    public Object doGet() {
        if (isProfileExist())
            return "main";

        return "loginPage";
    }

    @Override
    public Object doPost() {
        return login(req, resp);
    }

    public ViewInfo login(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("accountId");
        String pw = req.getParameter("accountPw");

        Profile profile = loginService.getProfile(id, pw);

        ViewInfo viewInfo = new ViewInfo("mainPage");
        if (profile == null) {
            viewInfo.setViewName("loginPage");
            return viewInfo;
        }

        viewInfo.setRedirectRequire(true);
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("profile", profile);

        return viewInfo;
    }
}
