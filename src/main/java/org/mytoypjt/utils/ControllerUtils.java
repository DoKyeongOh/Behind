package org.mytoypjt.utils;

import org.mytoypjt.models.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URL;

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

}
