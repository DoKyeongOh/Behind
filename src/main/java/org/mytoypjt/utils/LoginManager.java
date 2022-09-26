package org.mytoypjt.utils;

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
}
