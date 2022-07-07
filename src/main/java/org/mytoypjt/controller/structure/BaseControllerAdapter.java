package org.mytoypjt.controller.structure;

import org.mytoypjt.models.etc.ViewInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseControllerAdapter {

    IRequestControllerMapping requestControllerMapping;

    public BaseControllerAdapter(IRequestControllerMapping rcm) {
        this.requestControllerMapping = rcm;
    }

    public abstract ViewInfo execute(HttpServletRequest req, HttpServletResponse resp);

}
