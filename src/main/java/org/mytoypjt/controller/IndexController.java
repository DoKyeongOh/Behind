package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.etc.ViewInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController extends PropertiesControllerTemplete {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView showIndexPage(@SessionAttribute(name = "profile", required = false)Profile profile){
        if (profile != null) {
            System.out.println("로그인 정보가 남아있어서 메인 페이지로 이동~");
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setView(new RedirectView("/main/page"));
            return modelAndView;
        }
        return new ModelAndView("index");
    }

    /*@RequestMapping(uri = "/", method = "POST")
    public Object doPost() {
        return "index";
    }

    @RequestMapping(uri = "/", method = "GET")
    public ViewInfo showIndexPage(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        if (session.getAttribute("profile") != null)
            return ViewInfo.getRedirectViewInfo("/main/page");

        return new ViewInfo("index");
    }*/

}

