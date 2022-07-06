package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.ControllerTemplete;

import java.util.Iterator;

public class IndexController extends ControllerTemplete {

    @Override
    public Object doGet() {
        return "index";
    }

    @Override
    public Object doPost() {
        return "index";
    }
}

