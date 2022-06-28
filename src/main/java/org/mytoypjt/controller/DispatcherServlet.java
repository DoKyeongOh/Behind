package org.mytoypjt.controller;

import org.mytoypjt.utils.ViewResolver;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private ControllerFactory controllerFactory;

    @Override
    public void init() throws ServletException {
        controllerFactory = new ControllerFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IController iController = null;
        iController = controllerFactory.getController(req.getRequestURI());
        String page = "index";

        ViewResolver viewResolver = new ViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        if (iController != null)
            page = iController.execute(req, resp);

        page = viewResolver.getViewName(page);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
        requestDispatcher.forward(req, resp);
    }
}
