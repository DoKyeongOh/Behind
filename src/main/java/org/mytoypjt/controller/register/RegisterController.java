package org.mytoypjt.controller.register;

import org.mytoypjt.controller.structure.ControllerTemplete;

public class RegisterController extends ControllerTemplete {
    @Override
    public Object doGet() {
        if (isUserExist())
            return "main";
        return "register";
    }

    @Override
    public Object doPost() {
        return "register";
    }
}
