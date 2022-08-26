package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.service.LoginService;
import org.mytoypjt.service.annotation.Transaction;
import org.mytoypjt.utils.ControllerUtils;
import org.mytoypjt.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

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
    public ModelAndView getLoginSession(@RequestParam(name = "accountId")String id, @RequestParam(name = "accountPw")String pw, HttpSession session){
        Profile profile = loginService.getProfile(id, pw);

        if (profile == null)
            return new ModelAndView("loginPage");

        session.setAttribute("profile", profile);
        ModelAndView mv = new ModelAndView();
        mv.setView(new RedirectView("/main/page"));
        return mv;
    }

    @RequestMapping(path = "/login", method = RequestMethod.DELETE)
    public ModelAndView deleteLoginSession(HttpSession session){
        ModelAndView mv = new ModelAndView("/");
        Profile profile = (Profile) session.getAttribute("profile");
        if (profile == null) {
            session.setAttribute("profile", null);
            mv.setView(new RedirectView("/"));
            return mv;
        }
        return mv;
    }

}