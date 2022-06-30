package org.mytoypjt.controller.register;

import org.mytoypjt.controller.structure.ControllerTemplete;
import org.mytoypjt.entity.ModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterController extends ControllerTemplete {
    @Override
    public Object executeGetRequest() {
        return "register";
    }

    @Override
    public Object executePostRequest() {
        return "register";
    }
}
