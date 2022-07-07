package org.mytoypjt.controller.login.password;

import org.mytoypjt.controller.structure.PropertiesControllerTemplete;

public class ResetPwCompleteController extends PropertiesControllerTemplete {
    @Override
    public Object doGet() {
        return "pwResetComplete";
    }
}
