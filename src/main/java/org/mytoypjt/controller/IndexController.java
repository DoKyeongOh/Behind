package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.ControllerTemplete;

public class IndexController extends ControllerTemplete {

    @Override
    public Object executeGetRequest() {
        boolean test = isUserExist();
        if (isUserExist())
            return "main";
        return "index";
    }

    @Override
    public Object executePostRequest() {
        return "index";
    }
}

