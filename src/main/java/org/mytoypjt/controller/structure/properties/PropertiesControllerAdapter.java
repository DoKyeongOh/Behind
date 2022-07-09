package org.mytoypjt.controller.structure.properties;

import org.mytoypjt.controller.structure.BaseControllerAdapter;
import org.mytoypjt.controller.structure.RequestControllerMappingFactory;
import org.mytoypjt.controller.structure.enums.MappingName;
import org.mytoypjt.models.etc.ViewInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PropertiesControllerAdapter extends BaseControllerAdapter {

    public PropertiesControllerAdapter() {
        this.requestControllerMapping =
                RequestControllerMappingFactory.getMappingClass(MappingName.Properties);
        requestControllerMapping.entryControllers();
    }

    @Override
    public ViewInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        IController controller = (IController) requestControllerMapping.getController(uri);
        ViewInfo viewInfo = controller.execute(req, resp);
        return viewInfo;
    }
}
