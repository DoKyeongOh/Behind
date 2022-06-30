package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.ControllerTemplete;
import org.mytoypjt.entity.ModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindIdController extends ControllerTemplete {

    @Override
    public Object executeGetRequest() {
        return "findId";
    }

    @Override
    public Object executePostRequest() {
        return "findId";
    }
}
