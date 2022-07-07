package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.PropertiesControllerTemplete;

public class MainController extends PropertiesControllerTemplete {
    @Override
    public Object doGet() {
        if (!isUserExist())
            return "index";

        return "main";
    }

    @Override
    public Object doPost() {
        return "main";
    }


}
