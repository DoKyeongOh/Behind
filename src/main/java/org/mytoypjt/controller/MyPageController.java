package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.models.vo.ActivityVO;
import org.mytoypjt.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyPageController {
    public MyPageController(){

    }

    @RequestMapping(uri = "/my/page", method = "get")
    public ViewInfo showMyPage(HttpServletRequest req, HttpServletResponse resp){

        HttpSession session = req.getSession();
        Profile profile = (Profile) session.getAttribute("profile");

        if (profile == null)
            return ViewInfo.getRedirectViewInfo("/");

        MemberService memberService = new MemberService();
        ActivityVO activityVO = memberService.getActivities(profile);

        return new ViewInfo("myPage");
    }


}
