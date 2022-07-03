package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.ControllerTemplete;

import javax.servlet.http.Cookie;
import java.util.Arrays;

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
