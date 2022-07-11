package org.mytoypjt.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mytoypjt.models.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.net.URL;
import java.util.Map;

public class ControllerUtils {

    public static boolean isExistUserSession(HttpServletRequest req){

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            return false;
        if (user.getUserNo() < 0)
            return false;
        return true;
    }

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
    }

}
