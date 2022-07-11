package org.mytoypjt.controller.register;

import com.mysql.cj.xdevapi.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.models.entity.Account;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.service.RegisterService;
import org.mytoypjt.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class RegisterController {

    RegisterService registerService;

    public RegisterController(){
        registerService = new RegisterService();
    }

    @RequestMapping(uri = "/registerPage", method = "get")
    public String showRegisterPage(HttpServletRequest req, HttpServletResponse resp){
        if (ControllerUtils.isExistUserSession(req))
            return "mainPage";

        return "registerPage";
    }

    @RequestMapping(uri = "/registerPage", method = "post")
    public String showAccountInputPage(HttpServletRequest req, HttpServletResponse resp){
        String agreement = req.getParameter("isAgree");
        if (agreement == null) {
            req.setAttribute("noticeMessage", "약관에 동의해주세요!!");
            return "registerPage";
        }
        if (!agreement.equals("agree")) {
            req.setAttribute("noticeMessage", "약관에 동의해주세요!!");
            return "registerPage";
        }
        return "accountInputPage";
    }

    @RequestMapping(uri = "/account", method = "post")
    public ViewInfo entryAccount(HttpServletRequest req, HttpServletResponse resp){
        ViewInfo viewInfo = new ViewInfo("accountInputPage");

        String id = (String) req.getParameter("id");
        String pw = (String) req.getParameter("pw");
        String pwCheck = (String) req.getParameter("pwCheck");
        String email = (String) req.getParameter("email");
        String domain = (String) req.getParameter("domain");

        boolean isUsableId = registerService.isUsableAccountId(id);
        if (!isUsableId) {
            req.setAttribute("noticeMessage", "아이디를 이미 사용중입니다 !!");
            return viewInfo;
        }

        if (pw == null || pwCheck == null) {
            req.setAttribute("noticeMessage", "비밀번호를 입력해주세요 !!");
            return viewInfo;
        }

        if (!pw.equals(pwCheck)) {
            req.setAttribute("noticeMessage", "비밀번호와 비밀번호 확인이 다릅니다 !!");
            return viewInfo;
        }

        String rcvMailAddress = email + "@" + domain;
        registerService.sendAccountCert(rcvMailAddress);

        Account account = new Account(id, pw, rcvMailAddress);
        req.setAttribute("noticeMessage", "이메일에서 인증번호 확인 후 인증번호를 입력해주세요");
        return viewInfo;
    }

    @RequestMapping(uri = "/idUsage", method = "post")
    public ViewInfo checkSameId(HttpServletRequest req, HttpServletResponse resp){
        JSONObject jsonObject = ControllerUtils.getJsonObject(req);

        String newId = "";
        if (jsonObject.containsKey("id")) {
            newId = (String) jsonObject.get("id");
        }

        JSONObject respJsonObject = new JSONObject();

        boolean isUsable = registerService.isUsableAccountId(newId);
        respJsonObject.put("isUsable", isUsable);

        try {
            resp.getWriter().print(respJsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ViewInfo viewInfo = new ViewInfo();
        viewInfo.setContainView(false);

        return viewInfo;
    }
}
