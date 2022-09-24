package org.mytoypjt.interceptor;

import org.mytoypjt.utils.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    LoginManager loginManager;

    Map<String, Boolean> loginRequireUrlPatterns;

    public static HttpSession testSession;

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
        // 세션을 획득 후 싱글톤에 저장해뒀다가 다른 세션으로 들어와서 해당 세션이 없어졌는지 체크하는 테스트
        // 브라우저를 종료하더라도 사라지지 않음.
        // 그럼 중복 로그인을 어떻게 판별?
        // 먄약 중복 로그인을 고려하지 않는 로그인 기능이라면, 세션에 저장해놓기만 해도 상관 없음.
        // 중복 로그인을 고려해야 한다면, map <accountNo : session>으로 저장하기?
        //

        System.out.println(testSession);
        testSession = request.getSession();
        try {
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
        ServletContext sc = request.getServletContext();

        if (session.getAttribute("userInfo") != null) {
            response.sendRedirect("/main/page");
            return false;
        }
        return true;
    }

    public boolean exeLoginSessReq(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        ServletContext sc = request.getServletContext();

        if (session.getAttribute("userInfo") == null) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
