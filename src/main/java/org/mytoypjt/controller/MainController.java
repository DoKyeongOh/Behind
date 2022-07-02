package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.ControllerTemplete;
import org.mytoypjt.entity.User;

import javax.servlet.http.HttpSession;

public class MainController extends ControllerTemplete {
    @Override
    public Object executeGetRequest() {
        if (!isUserExist())
            return "index";

        return "main";
    }

    @Override
    public Object executePostRequest() {
        return "main";
    }


}
