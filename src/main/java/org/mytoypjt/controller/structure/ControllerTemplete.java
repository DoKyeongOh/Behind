package org.mytoypjt.controller.structure;

import org.mytoypjt.entity.ModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ControllerTemplete implements IController {

    protected HttpServletRequest req;
    protected HttpServletResponse resp;

    @Override
    public ModelView execute(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;

        Object object = null;
        ModelView modelView = null;

        switch (req.getMethod()) {
            case "GET" : object = executeGetRequest();

            case "POST" : object = executePostRequest();
        }

        if (object instanceof String)
            modelView = new ModelView((String) object);
        else if (object instanceof ModelView)
            modelView = (ModelView) object;
        else
            modelView = new ModelView("pageNotFound");

        return modelView;
    }

    public abstract Object executeGetRequest();
    public abstract Object executePostRequest();
}
