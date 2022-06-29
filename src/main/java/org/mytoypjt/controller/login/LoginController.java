package org.mytoypjt.controller.login;

import org.mytoypjt.controller.ControllerTemplete;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends ControllerTemplete {

    @Override
    public String executeGetRequest(HttpServletRequest req, HttpServletResponse resp) {
        return "login";
    }

    @Override
    public String executePostRequest(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        String userPw = req.getParameter("userPw");

        req.setAttribute("userId", userId);
        req.setAttribute("userPw", userPw);

        // DB 쿼리로 계정 존재 여부 체크

        // new User();


        return "login";
    }
}
