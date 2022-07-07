package org.mytoypjt.servlet;

import org.mytoypjt.controller.structure.*;
import org.mytoypjt.controller.structure.enums.MappingName;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.utils.ResourceUtil;
import org.mytoypjt.utils.ViewResolver;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private IRequestControllerMapping contollerMapping;
    private BaseControllerAdapter controllerAdapter;
    String rootPath = "";
    ViewResolver viewResolver;

    MappingName mappingName;

    @Override
    public void init() throws ServletException {
        mappingName = getMappingMethod();

        controllerAdapter = ControllerAdapterFactory.getControllerAdapter(mappingName);
        contollerMapping = RequestControllerMappingFactory.getMappingClass(mappingName);
        contollerMapping.entryController();

        rootPath = getServletContext().getContextPath();
        rootPath = getServletContext().getRealPath(rootPath);

        viewResolver = new ViewResolver(rootPath);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();

        controllerAdapter = ControllerAdapterFactory.getControllerAdapter(mappingName);
        ViewInfo viewInfo = controllerAdapter.execute(req, resp);


        IController controller = (IController) contollerMapping.getController(req.getRequestURI());
        ViewInfo viewInfo = controller.execute(req, resp);
        String viewName = viewResolver.getViewName(viewInfo.getViewName());

        if (viewInfo.isRedirectRequire()) {
            resp.sendRedirect(viewInfo.getViewName());
            return;
        }
        
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewName);
        requestDispatcher.forward(req, resp);
    }

    public MappingName getMappingMethod(){
        ResourceUtil resourceUtil = new ResourceUtil("/config.properties");
        String method = (String) resourceUtil.getProperty("controller.mapping.method");
        method = method.toLowerCase();
        if (method.equals("annotation"))
            return MappingName.Annotation;
        else if (method.equals("properties"))
            return MappingName.Properties;
        else return MappingName.Properties;
    }

}
