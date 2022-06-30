package org.mytoypjt.controller;

import org.mytoypjt.controller.structure.ControllerTemplete;
import org.mytoypjt.entity.ModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends ControllerTemplete {
    @Override
    public Object executeGetRequest() {
        return "main";
    }

    @Override
    public Object executePostRequest() {
        return "main";
    }
}
