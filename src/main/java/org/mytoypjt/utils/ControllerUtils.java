package org.mytoypjt.utils;

import org.mytoypjt.controller.consts.SessionConst;
import org.mytoypjt.models.entity.Profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ControllerUtils {

    public static boolean isExistProfileSession(HttpServletRequest req){

        HttpSession session = req.getSession();
        Profile profile = (Profile) session.getAttribute(SessionConst.USER_PROFILE);
        if (profile == null)
            return false;
        if (profile.getAccountNo() < 0)
            return false;
        return true;
    }
/*
    public static JSONObject getJsonObject(HttpServletRequest req){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            BufferedReader reader = req.getReader();
            jsonObject = (JSONObject) jsonParser.parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }*/

}
