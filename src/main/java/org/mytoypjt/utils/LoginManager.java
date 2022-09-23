package org.mytoypjt.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoginManager {

    Map<Integer, Integer> loginMap; // <account-no:session-id>

    int sessionIdLength = 7;

    public LoginManager(){
        loginMap = new HashMap<>();
    }

    public boolean addLoginSession(int accountNo){
        int id = getNewSessionId();
        loginMap.values().stream().filter(sessId -> {
           return Integer.parseInt()
        });


        return true;
    }

    public void removeLoginSession(String sessionId){
        loginMap.remove(sessionId);
    }


    public int getNewSessionId() {
        return (int)(Math.random() * Math.pow(10, sessionIdLength));
    }
}
