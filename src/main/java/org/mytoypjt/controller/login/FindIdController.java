package org.mytoypjt.controller.login;

import org.mytoypjt.controller.ControllerTemplete;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindIdController extends ControllerTemplete {

    @Override
    public String executeGetRequest(HttpServletRequest req, HttpServletResponse resp) {
        return "findId";
    }

    @Override
    public String executePostRequest(HttpServletRequest req, HttpServletResponse resp) {
        return "findId";
    }
}
