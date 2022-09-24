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

    public boolean addLoginSession(int accountNo, HttpSession session){
        if (loginMap.containsKey(accountNo))
            return false;

        loginMap.put(accountNo, session);
        return true;
    }

    public void removeLoginSession(int accountNo){
        loginMap.remove(accountNo);
    }

    public void changeLoginSession(int accountNo, HttpSession session){
        loginMap.replace(accountNo, session);
    }
}
