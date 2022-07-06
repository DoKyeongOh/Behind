package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.ControllerTemplete;

import javax.servlet.http.Cookie;
import java.util.Arrays;

public class MainController extends ControllerTemplete {
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
