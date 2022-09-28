package org.mytoypjt.utils;

import org.mytoypjt.models.entity.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginManager {

    Map<Integer, HttpSession> loginMap;

    public LoginManager(){
        loginMap = new HashMap<>();
    }

    public boolean addLoginSession(int accountNo, HttpSession session) {
        if (loginMap.containsKey(accountNo)) {
            // 중복 로그인 확인 및 로그인 변경 질의 창으로 리다이렉트하도록.
            return false;
        }

        loginMap.put(accountNo, session);

        return true;
    }

    public void removeLoginSession(int accountNo){
        loginMap.remove(accountNo);
    }

    public void changeLoginSession(int accountNo, HttpSession session){
        loginMap.replace(accountNo, session);
    }


    public boolean isCorrectProfile(int accountNo, HttpSession session){
        if (session == null)
            return false;

        if (!loginMap.get(accountNo).equals(session))
            return false;

        Profile profile = (Profile) session.getAttribute("profile");
        if (profile == null)
            return false;

        if (!loginMap.get(accountNo).getAttribute("profile").equals(profile))
            return false;

        return true;
    }
}
