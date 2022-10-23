package org.mytoypjt.controller;

import org.mytoypjt.models.etc.AbstractEntityLog;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MyPageController {

    @Autowired
    MemberService memberService;

    public MyPageController(){}

    @GetMapping(path = "/my/page")
    public ModelAndView showMyPage(@SessionAttribute(name = "profile", required = false) Profile profile){
        if (profile == null)
            return new ModelAndView(new RedirectView("/"));

        List<AbstractEntityLog> logList =
                memberService.getLogsByAccountNo(profile.getAccountNo(), 100);

        List<String> loggingPathList = memberService.getLoggingPathList(logList);

        ModelAndView mv = new ModelAndView("myPage");
        mv.addObject("logList", logList);
        mv.addObject("loggingPathList", loggingPathList);
        return mv;
    }




}
