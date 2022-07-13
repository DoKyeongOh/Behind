package org.mytoypjt.controller.register;

import org.json.simple.JSONObject;
import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.models.dto.AccountCertDTO;
import org.mytoypjt.models.entity.Account;
import org.mytoypjt.models.entity.User;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.service.RegisterService;
import org.mytoypjt.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class RegisterController {

    final String ACCOUNT_CERT_KEY = "accountCert";
    final String ACCOUNT_NO = "accountNo";


    RegisterService registerService;

    public RegisterController(){
        registerService = new RegisterService();
    }

    @RequestMapping(uri = "/register/page/1", method = "get")
    public String showRegisterPage(HttpServletRequest req, HttpServletResponse resp){
        if (ControllerUtils.isExistUserSession(req))
            return "mainPage";

        return "registerPage";
    }

    @RequestMapping(uri = "/register/page/2", method = "get")
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

        HttpSession session = req.getSession();
        session.setAttribute(ACCOUNT_CERT_KEY, null);

        return "accountInputPage";
    }

    @RequestMapping(uri = "/register/page/3", method = "get")
    public String showProfileInputPage(HttpServletRequest req, HttpServletResponse resp){
        return "profileInputPage";
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

        if (!registerService.isCorrectPw(pw, pwCheck)) {
            req.setAttribute("noticeMessage", "비밀번호를 확인해주세요 !!");
            return viewInfo;
        }

        String rcvMailAddress = email + "@" + domain;
        Account account = new Account(id, pw, rcvMailAddress);

        AccountCertDTO dto = registerService.sendAccountCert(account);
        dto.setAccount(account);

        HttpSession session = req.getSession();
        session.setAttribute(ACCOUNT_CERT_KEY, dto);

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

    @RequestMapping(uri = "/account/cert", method = "post")
    public ViewInfo checkAccountCert(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        AccountCertDTO dto = (AccountCertDTO) session.getAttribute(ACCOUNT_CERT_KEY);
        String inputValue = req.getParameter("accountCertInput");

        RegisterService.CertErrorType type = registerService.getCertErrorType(dto, inputValue);
        String errorMessage = registerService.getCertErrorMessage(type);

        ViewInfo viewInfo = new ViewInfo();

        if (!errorMessage.equals("")) {
            req.setAttribute("noticeMessage", errorMessage);
            return new ViewInfo("accountInputPage");
        }

        boolean successed = registerService.createAccount(dto.getAccount());
        if (!successed) {
            req.setAttribute("noticeMessage", "예상치 못한 오류가 발생했습니다 다시 시도해주세요.");
            return new ViewInfo("accountInputPage");
        }

        registerService.createDefaultUser(dto.getAccount());

        session.setAttribute(ACCOUNT_CERT_KEY, null);
        session.setAttribute(ACCOUNT_NO, dto.getAccount().getAccountNo());

        viewInfo.setRedirectTo("/register/page/3");
        return viewInfo;
    }

    @RequestMapping(uri = "/profile", method = "post")
    public ViewInfo updateUser(HttpServletRequest req, HttpServletResponse resp){

        String nicname = req.getParameter("nicname");
        String age = req.getParameter("age");
        String city = req.getParameter("city");
        String gender = req.getParameter("genderSelector");

        HttpSession session = req.getSession();
        int accountNo = (int) session.getAttribute(ACCOUNT_NO);
        session.setAttribute(ACCOUNT_NO, null);

        User user = new User(accountNo, nicname, new Date(), city, Integer.parseInt(age), gender, 1);
        boolean successed = registerService.updateUser(user);

        if (!successed)
            return new ViewInfo("profileInputPage");

        ViewInfo viewInfo = new ViewInfo();
        viewInfo.setRedirectTo("/");
        return viewInfo;
    }
}
