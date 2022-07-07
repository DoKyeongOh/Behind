package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;

import javax.servlet.http.HttpServletRequest;

public class IndexController extends PropertiesControllerTemplete {

    @Override
    public Object doGet() {
        return "index";
    }

    @RequestMapping(uri = "/", method = "POST")
    @Override
    public Object doPost() {
        return "index";
    }

    @RequestMapping(uri = "/", method = "GET")
    public String test(){
        return "index";
    }
}

