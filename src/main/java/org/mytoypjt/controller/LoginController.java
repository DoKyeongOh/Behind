package org.mytoypjt.controller;

import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.service.LoginService;
import org.mytoypjt.utils.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

        session.setAttribute("profile", profile);
        if (!loginManager.addLoginSession(profile.getAccountNo(), session)) {
            return new ModelAndView("loginFailPage");
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("test", "it is test");
        mv.setView(new RedirectView("/main/page"));
        return mv;
    }

    @PutMapping(path = "/login")
    public ModelAndView putLoginSession(@SessionAttribute(name = "profile", required = false) Profile profile,
                                        HttpSession session) {
        loginManager.changeLoginSession(profile.getAccountNo(), session);
        return new ModelAndView(new RedirectView("/main/page"));
    }

    @DeleteMapping(path = "/login")
    public ModelAndView deleteLoginSession(HttpSession session) {
        Profile profile = (Profile) session.getAttribute("profile");
        session.setAttribute("profile", null);
        loginManager.removeLoginSession(profile.getAccountNo());
        ModelAndView mv = new ModelAndView(new RedirectView("/"));
        return mv;
    }

}
