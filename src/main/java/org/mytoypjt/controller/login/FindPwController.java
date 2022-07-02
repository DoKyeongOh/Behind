package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.ControllerTemplete;
import org.mytoypjt.service.FindAccountService;

import javax.servlet.http.HttpSession;

public class FindPwController extends ControllerTemplete {

    @Override
    public Object executeGetRequest() {
        if (isUserExist())
            return "main";
        return "findPw";
    }

    @Override
    public Object executePostRequest() {
        return "findPw";
    }
}
