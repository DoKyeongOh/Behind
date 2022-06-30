package org.mytoypjt.controller.structure;

import org.mytoypjt.entity.ModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IController {
    public ModelView execute(HttpServletRequest req, HttpServletResponse resp);
}
