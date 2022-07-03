package org.mytoypjt.controller.structure;

import org.mytoypjt.models.etc.ViewInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IController {
    public ViewInfo execute(HttpServletRequest req, HttpServletResponse resp);
}
