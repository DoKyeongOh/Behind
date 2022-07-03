package org.mytoypjt.servlet;

import org.mytoypjt.controller.structure.IController;
import org.mytoypjt.controller.structure.RequestControllerMapping;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.utils.ViewResolver;

import javax.servlet.*;
import javax.servlet.http.*;
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

        ViewInfo viewInfo = controller.execute(req, resp);
        String viewName = viewResolver.getViewName(viewInfo.getViewName());

        if (viewInfo.isRedirectRequire()) {
            resp.sendRedirect(viewInfo.getViewName());
            return;
        }
        
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewName);
        requestDispatcher.forward(req, resp);
    }
}
