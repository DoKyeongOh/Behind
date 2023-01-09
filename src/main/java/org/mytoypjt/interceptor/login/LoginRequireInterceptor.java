package org.mytoypjt.interceptor.login;

import org.mytoypjt.consts.SessionConst;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginRequireInterceptor implements HandlerInterceptor {

    @Autowired
    LoginService loginService;

    public LoginRequireInterceptor(){}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Profile profile = (Profile) session.getAttribute(SessionConst.USER_PROFILE);
        if (profile == null){
            response.sendRedirect("/");
            return false;
        }

        if (!loginService.isProfileUsable(profile.getAccountNo(), session)) {
            response.sendRedirect("/");
            return false;
        }


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
