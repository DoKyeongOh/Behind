package org.mytoypjt.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DispatcherServlet extends HttpServlet {
    private ControllerFactory controllerFactory;

    @Override
    public void init() throws ServletException {
        String loc = getInitParameter("contextConfigLocation");
//        controllerFactory = new ControllerFactory(loc);


        //
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/index_test.jsp");
        requestDispatcher.forward(req, resp);
    }
}
