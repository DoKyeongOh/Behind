package org.mytoypjt.controller.register;

import org.mytoypjt.controller.ControllerTemplete;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterController extends ControllerTemplete {
    @Override
    public String executeGetRequest(HttpServletRequest req, HttpServletResponse resp) {
        return "register";
    }

    @Override
    public String executePostRequest(HttpServletRequest req, HttpServletResponse resp) {
        return "register";
    }
}
