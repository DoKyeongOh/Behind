package org.mytoypjt.controller;

import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MyPageController {

    @Autowired
    MemberService memberService;

    public MyPageController(){}

    @GetMapping(path = "/my/page")
    public ModelAndView showMyPage(@SessionAttribute(name = "profile", required = false) Profile profile){
        if (profile == null)
            return new ModelAndView(new RedirectView("/"));

//        ActivityVO activityVO = memberService.getActivities(profile);
        return new ModelAndView("myPage");
    }




}
