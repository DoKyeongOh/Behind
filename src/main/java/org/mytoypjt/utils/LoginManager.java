package org.mytoypjt.utils;

import org.mytoypjt.models.entity.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginManager {

    Map<Integer, HttpSession> loginMap;

    public LoginManager(){
        loginMap = new HashMap<>();
    }

    public boolean addLoginSession(int accountNo, HttpSession session) {
        if (loginMap.containsKey(accountNo))
            return false;

        loginMap.put(accountNo, session);
        if (loginMap.isEmpty())
            return true;

        int count = 0;
        for (int no : loginMap.keySet()) {
            System.out.println(count + ". no - " + no + ", information - " + loginMap.get(no).getAttribute("profile"));
        }

        return true;
    }

    public void removeLoginSession(int accountNo){
        System.out.println("logout : " + accountNo);
        int count = 0;
        for (int no : loginMap.keySet()) {
            System.out.println(count + ". no - " + no + ", information - " + loginMap.get(no).getAttribute("profile"));
        }
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
