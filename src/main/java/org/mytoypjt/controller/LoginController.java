package org.mytoypjt.controller;

import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.service.LoginService;
import org.mytoypjt.utils.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    LoginManager loginManager;

    public LoginController() {
    }

    @RequestMapping(path = "/login/page", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@SessionAttribute(name = "profile", required = false)Profile profile){
        ModelAndView modelAndView = new ModelAndView();
        if (profile != null)
            modelAndView.setView(new RedirectView("/main/page"));

        return new ModelAndView("loginPage");
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView getLoginSession(
            @RequestParam(name = "accountId")String id,
            @RequestParam(name = "accountPw")String pw,
            HttpSession session){

        Profile profile = null;
        try {
            profile = loginService.getProfile(id, pw);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (profile == null)
            return new ModelAndView("loginPage");

        loginManager.addLoginSession(profile.getAccountNo(), session);

        session.setAttribute("profile", profile);
        ModelAndView mv = new ModelAndView();
        mv.setView(new RedirectView("/main/page"));
        return mv;
    }

    @DeleteMapping(path = "/login")
    public ModelAndView deleteLoginSession(HttpSession session) {
        Profile profile = (Profile) session.getAttribute("profile");
        if (profile != null)
            loginManager.removeLoginSession(profile.getAccountNo());

        session.setAttribute("profile", null);
        ModelAndView mv = new ModelAndView(new RedirectView("/"));
        return mv;
    }

}
