package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.ControllerTemplete;
import org.mytoypjt.entity.ModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindPwController extends ControllerTemplete {

    @Override
    public Object executeGetRequest() {
        return "findPw";
    }

    @Override
    public Object executePostRequest() {
        return "findPw";
    }
}
