package org.mytoypjt.controller.register;

import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;

public class RegisterController extends PropertiesControllerTemplete {
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
