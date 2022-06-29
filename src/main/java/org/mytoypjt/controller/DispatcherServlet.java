package org.mytoypjt.controller;

import org.mytoypjt.utils.ViewResolver;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private RequestControllerMapping contollerMapping;
    String rootPath = "";
    ViewResolver viewResolver;

    @Override
    public void init() throws ServletException {
        contollerMapping = new RequestControllerMapping();
        rootPath = getServletContext().getContextPath();
        rootPath = getServletContext().getRealPath(rootPath);

        viewResolver = new ViewResolver(rootPath);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IController controller = null;
        controller = contollerMapping.getHandler(req.getRequestURI());
        String uri = req.getRequestURI();

        String page = controller.execute(req, resp);
        page = viewResolver.getViewName(page);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
        requestDispatcher.forward(req, resp);
    }
}
