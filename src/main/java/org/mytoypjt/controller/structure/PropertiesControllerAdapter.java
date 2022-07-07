package org.mytoypjt.controller.structure;

import org.mytoypjt.models.etc.ViewInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PropertiesControllerAdapter implements BaseControllerAdapter {

    public PropertiesControllerAdapter() {
    }

    @Override
    public ViewInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        IController controller = (IController) contollerMapping.getController(req.getRequestURI());
        ViewInfo viewInfo = controller.execute(req, resp);
        return viewInfo;
    }
}
