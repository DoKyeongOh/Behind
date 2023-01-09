package org.mytoypjt.controller;

import org.mytoypjt.consts.SessionConst;
import org.mytoypjt.models.dto.login.LoginRequestDTO;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.service.LoginService;
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

    public LoginController() {
    }

    @GetMapping(path = "/login/page")
    public String loginPage(){
        return "loginPage";
    }

    @PostMapping(path = "/login")
    public ModelAndView login(LoginRequestDTO dto, HttpSession session){
        loginService.login(dto, session);
        return new ModelAndView(new RedirectView("/main/page"));
    }

    @PutMapping(path = "/login")
    public ModelAndView reLogin(@SessionAttribute(name = "profile", required = false) Profile profile,
                                HttpSession session) {
        loginService.changeLoginSession(profile.getAccountNo(), session);
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
