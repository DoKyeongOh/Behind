package org.mytoypjt.interceptor.login;

import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginNotRequireInterceptor implements HandlerInterceptor {

    @Autowired
    LoginManager loginManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Profile profile = (Profile) session.getAttribute("profile");
        if (profile == null)
            return true;

        if (loginManager.isCorrectProfile(profile.getAccountNo(), session)){
            response.sendRedirect("/main/page");
            return false;
        }

        session.setAttribute("profile", null);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
