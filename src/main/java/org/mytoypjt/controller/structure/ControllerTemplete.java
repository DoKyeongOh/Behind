package org.mytoypjt.controller.structure;

import org.mytoypjt.entity.User;
import org.mytoypjt.servlet.ViewInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class ControllerTemplete implements IController {

    protected HttpServletRequest req;
    protected HttpServletResponse resp;

    @Override
    public ViewInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;

        Object object = null;
        ViewInfo viewInfo = null;

        switch (req.getMethod()) {
            case "GET" : {
                object = executeGetRequest();
                break;
            }

            case "POST" : {
                object = executePostRequest();
                break;
            }
        }

        if (object instanceof String)
            viewInfo = new ViewInfo((String) object);
        else if (object instanceof ViewInfo)
            viewInfo = (ViewInfo) object;
        else
            viewInfo = new ViewInfo("pageNotFound");

        return viewInfo;
    }

    public boolean isUserExist(){
        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("user");
        if (user == null)
            return false;
        return true;
    }

    public abstract Object executeGetRequest();
    public abstract Object executePostRequest();

}
