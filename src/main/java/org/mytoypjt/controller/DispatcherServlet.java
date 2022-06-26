package org.mytoypjt.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DispatcherServlet extends HttpServlet {
    private ControllerFactory controllerFactory;

    @Override
    public void init() throws ServletException {
        String loc = getInitParameter("contextConfigLocation");
        controllerFactory = new ControllerFactory();

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IController iController = null;
        iController = controllerFactory.getController(req.getRequestURI());
        String page = "/WEB-INF/views/index_test.jsp";

        if (iController != null) {
            page = iController.execute(req, resp);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
        requestDispatcher.forward(req, resp);
    }
}
