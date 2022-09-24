package org.mytoypjt.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginManager {

    Map<Integer, Integer> loginMap;

    public LoginManager(){
        loginMap = new HashMap<>();
    }

    public boolean addLoginSession(int accountNo, int sessionId){
        if (loginMap.containsKey(accountNo))
            return false;

        loginMap.put(accountNo, sessionId);
        return true;
    }

    public void removeLoginSession(int accountNo){
        loginMap.remove(accountNo);
    }

}
