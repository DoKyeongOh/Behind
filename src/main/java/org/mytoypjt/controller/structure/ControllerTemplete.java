package org.mytoypjt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ControllerTemplete implements IController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        switch (req.getMethod()) {
            case "GET" : return executeGetRequest(req,resp);

            case "POST" : return executePostRequest(req,resp);

        }
        return "pageNotFound";
    }

    public abstract String executeGetRequest(HttpServletRequest req, HttpServletResponse resp);
    public abstract String executePostRequest(HttpServletRequest req, HttpServletResponse resp);
}
