package org.mytoypjt.controller;

import org.mytoypjt.controller.consts.SessionConst;
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

    @PostMapping(path = "/login")
    public ModelAndView login(LoginRequestDTO dto, HttpSession session){
        loginService.login(dto, session);
        return new ModelAndView(new RedirectView("/main/page"));
    }

    @PutMapping(path = "/login")
    public ModelAndView putLoginSession(@SessionAttribute(name = "profile", required = false) Profile profile,
                                        HttpSession session) {
        loginManager.changeLoginSession(profile.getAccountNo(), session);
        return new ModelAndView(new RedirectView("/main/page"));
    }

    @DeleteMapping(path = "/login")
    public ModelAndView deleteLoginSession(HttpSession session) {
        Profile profile = (Profile) session.getAttribute(SessionConst.USER_PROFILE);
        try {
            loginService.logout(profile.getAccountNo());
        } catch(Exception e) {
            e.printStackTrace();
        }

        session.setAttribute(SessionConst.USER_PROFILE, null);
        loginManager.removeLoginSession(profile.getAccountNo());
        ModelAndView mv = new ModelAndView(new RedirectView("/"));
        return mv;
    }

}
