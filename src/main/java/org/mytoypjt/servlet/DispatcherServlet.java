package org.mytoypjt.servlet;

import org.mytoypjt.controller.structure.BaseControllerAdapter;
import org.mytoypjt.controller.structure.ControllerAdapterFactory;
import org.mytoypjt.controller.structure.enums.MappingName;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.utils.DBUtil;
import org.mytoypjt.utils.PropertiesUtil;
import org.mytoypjt.utils.ViewResolver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class DispatcherServlet extends HttpServlet {
    private BaseControllerAdapter controllerAdapter;
    String rootPath = "";
    ViewResolver viewResolver;
    MappingName mappingName;

    @Override
    public void init() throws ServletException {
        mappingName = getMappingMethod();
        controllerAdapter = ControllerAdapterFactory.getControllerAdapter(mappingName);

        DBUtil.getBasicDataSource();

        rootPath = getServletContext().getContextPath();
        rootPath = getServletContext().getRealPath(rootPath);

        viewResolver = new ViewResolver(rootPath);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ViewInfo viewInfo = controllerAdapter.execute(req, resp);

        if (!viewInfo.isContainView()) {
            resp.setCharacterEncoding("UTF-8");
            return;
        }

        String viewName = viewResolver.getViewName(viewInfo);

        if (viewInfo.isRedirectRequire()) {
            String contextPath = getServletContext().getContextPath();
            resp.sendRedirect(contextPath + viewName);
            return;
        }
        
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewName);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int a = 32;
        this.service(req, resp);
    }

    public MappingName getMappingMethod(){
        PropertiesUtil propertiesUtil = new PropertiesUtil("/annotation_config.properties");
        String method = (String) propertiesUtil.getProperty("controller.mapping.method");
        method = method.toLowerCase().trim();

        if (method.equals("annotation"))
            return MappingName.Annotation;
        else if (method.equals("properties"))
            return MappingName.Properties;
        else return MappingName.Properties;
    }

}
