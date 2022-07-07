package org.mytoypjt.controller.structure;

import lombok.Getter;
import lombok.Setter;
import org.mytoypjt.controller.structure.IRequestControllerMapping;
import org.mytoypjt.models.etc.ViewInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Getter @Setter
public abstract class BaseControllerAdapter {

    protected IRequestControllerMapping requestControllerMapping;

    public BaseControllerAdapter() {
    }

    public abstract ViewInfo execute(HttpServletRequest req, HttpServletResponse resp);

}
