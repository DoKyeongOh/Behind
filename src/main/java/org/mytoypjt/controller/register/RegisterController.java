package org.mytoypjt.controller.register;

import org.mytoypjt.controller.structure.ControllerTemplete;

public class RegisterController extends ControllerTemplete {
    @Override
    public Object executeGetRequest() {
        if (isUserExist())
            return "main";
        return "register";
    }

    @Override
    public Object executePostRequest() {
        return "register";
    }
}
