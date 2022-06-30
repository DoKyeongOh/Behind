package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.ControllerTemplete;
import org.mytoypjt.dao.LoginDao;
import org.mytoypjt.entity.ModelView;
import org.mytoypjt.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends ControllerTemplete {

    @Override
    public Object executeGetRequest() {
        return "login";
    }

    @Override
    public Object executePostRequest() {
        String userId = req.getParameter("userId");
        String userPw = req.getParameter("userPw");

        req.setAttribute("userId", userId);
        req.setAttribute("userPw", userPw);

        // DB 쿼리로 계정 존재 여부 체크
        LoginDao loginDao = new LoginDao();
        int accountNo = loginDao.getUserId(userId, userPw);

        if (!User.isCorrectUserNo(accountNo)) return new ModelView("login");
        User user = loginDao.getUser(accountNo);

        ModelView modelView = new ModelView("main");

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user", user);

        return modelView;
    }
}
