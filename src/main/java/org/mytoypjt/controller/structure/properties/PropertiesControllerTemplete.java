package org.mytoypjt.controller.structure.properties;

import org.mytoypjt.models.entity.User;
import org.mytoypjt.models.etc.ViewInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class PropertiesControllerTemplete implements IController {

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
                object = doGet();
                break;
            }

            case "POST" : {
                object = doPost();
                break;
            }

            case "PUT" : {
                object = doPut();
                break;
            }

            case "DELETE" : {
                object = doDelete();
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

    public Object doGet(){
        return null;
    };
    public Object doPost(){
        return null;
    };
    public Object doPut(){
        return null;
    };
    public Object doDelete(){
        return null;
    };

}
