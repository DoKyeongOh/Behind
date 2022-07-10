package org.mytoypjt.controller.register;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterController {
    @RequestMapping(uri = "/registerPage", method = "get")
    public String showRegisterPage(HttpServletRequest req, HttpServletResponse resp){
        if (ControllerUtils.isExistUserSession(req))
            return "mainPage";

        return "registerPage";
    }

    @RequestMapping(uri = "/registerPage", method = "post")
    public String showAccountInputPage(HttpServletRequest req, HttpServletResponse resp){

        String agreement = req.getParameter("isAgree");
        if (agreement == null) {
            req.setAttribute("noticeMessage", "약관에 동의해주세요!!");
            return "registerPage";
        }

        if (!agreement.equals("agree")) {
            req.setAttribute("noticeMessage", "약관에 동의해주세요!!");
            return "registerPage";
        }

        return "accountInputPage";
    }

    @RequestMapping(uri = "/register", method = "post")
    public String entryAccount(){
        return "accountInputPage";
    }
}
