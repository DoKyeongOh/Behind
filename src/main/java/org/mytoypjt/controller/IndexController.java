package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.controller.structure.PropertiesControllerTemplete;

public class IndexController extends PropertiesControllerTemplete {

    @RequestMapping(uri = "/", method = "GET")
    @Override
    public Object doGet() {
        return "index";
    }

    @RequestMapping(uri = "/", method = "POST")
    @Override
    public Object doPost() {
        return "index";
    }

    private void test(){}
}

