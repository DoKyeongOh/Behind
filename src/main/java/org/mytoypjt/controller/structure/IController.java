package org.mytoypjt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IController {
    public String execute(HttpServletRequest req, HttpServletResponse resp);

}
