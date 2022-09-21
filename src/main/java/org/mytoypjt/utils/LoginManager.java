package org.mytoypjt.utils;

import org.mytoypjt.models.entity.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginManager {

    Map<String, Date> loginMap; // <session-id:expired-date>

    public LoginManager(){
        loginMap = new HashMap<>();
    }

    public boolean add(Profile profile){
        return true;
    }

}
