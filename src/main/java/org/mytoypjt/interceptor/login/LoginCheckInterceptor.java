package org.mytoypjt.interceptor.login;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginCheckInterceptor implements HandlerInterceptor {

    Map<String, Boolean> loginRequireUrlPatterns;

    public LoginCheckInterceptor(){
        loginRequireUrlPatterns = new HashMap<>();
        loginRequireUrlPatterns.put("/register/page/{pageNo}", true);
        loginRequireUrlPatterns.put("/account", true);
        loginRequireUrlPatterns.put("/account/cert", true);
        loginRequireUrlPatterns.put("/profile", true);
        loginRequireUrlPatterns.put("/login/page", true);
        loginRequireUrlPatterns.put("/login", true);
        loginRequireUrlPatterns.put("/id/page", true);
        loginRequireUrlPatterns.put("/id/cert", true);
        loginRequireUrlPatterns.put("/pw/page/{pageNo}", true);
        loginRequireUrlPatterns.put("/pw/cert", true);
        loginRequireUrlPatterns.put("/pw", true);
        loginRequireUrlPatterns.put("/", true);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if (request.getRequestURI().equals("/login"))
                return true;

            if (loginRequireUrlPatterns.containsKey(request.getRequestURI()))
                return exeLoginSessNotReq(request, response);
            else
                return exeLoginSessReq(request, response);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    public boolean exeLoginSessNotReq(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("profile") != null) {
            response.sendRedirect("/main/page");
            return false;
        }
        return true;
    }

    public boolean exeLoginSessReq(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("profile") == null) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
