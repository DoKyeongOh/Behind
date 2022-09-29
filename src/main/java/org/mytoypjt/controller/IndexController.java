package org.mytoypjt.controller;

import org.mytoypjt.models.entity.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView showIndexPage(@SessionAttribute(name = "profile", required = false)Profile profile){
        return new ModelAndView("index");
    }

}

