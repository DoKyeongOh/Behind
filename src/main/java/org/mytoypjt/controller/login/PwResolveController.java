package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.models.dto.PwCertificationInfo;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.service.FindAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PwResolveController {

    FindAccountService findAccountService;

    public PwResolveController(){
        findAccountService = new FindAccountService();
    }

    @RequestMapping(uri = "/findPwPage", method = "get")
    public String showFindPwPage(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        session.setAttribute("pwCertificationInfo", null);
        return "findPwPage";
    }

    @RequestMapping(uri = "/pwCertification", method="get")
    public String createPwCertification(HttpServletRequest req, HttpServletResponse resp){
        String id = (String) req.getParameter("id");
        String email = (String) req.getParameter("email");
        String domain = (String) req.getParameter("domain");
        String emailAddress = email + "@" + domain;

        PwCertificationInfo certificationInfo = findAccountService.getPwCertification(id, emailAddress);
        if (certificationInfo== null) {
            req.setAttribute("noticeMessage", "입력 정보가 잘못되었습니다 !!");
            return "findPwPage";
        }

        req.setAttribute("noticeMessage", "이메일을 확인해주세요 !!");

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("pwCertificationInfo", certificationInfo);
        return "findPwPage";
    }

    @RequestMapping(uri = "/pwCertification", method = "post")
    public ViewInfo removePwCertification(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        PwCertificationInfo certificationInfo =
                (PwCertificationInfo) session.getAttribute("pwCertificationInfo");
        String inputValue = (String) req.getParameter("pwCertificationInput");

        ViewInfo viewInfo = new ViewInfo("findPwPage");

        if (certificationInfo == null) {
            req.setAttribute("noticeMessage", "인증을 재시도해주세요 !!");
            return viewInfo;
        }

        if (!certificationInfo.getCertificationValue().equals(inputValue)) {
            req.setAttribute("noticeMessage", "인증번호를 확인 후 재시도해주세요 !!");
            return viewInfo;
        }

        viewInfo.setRedirectRequired();
        viewInfo.setViewName("pwResetPage");

        return viewInfo;
    }

    @RequestMapping(uri = "/pwResetPage", method = "get")
    public String showPwResetPage(){
        return "pwResetPage";
    }

    @RequestMapping(uri = "/pwReset", method = "post")
    public ViewInfo resetAccountPw(HttpServletRequest req, HttpServletResponse resp){
        ViewInfo viewInfo = new ViewInfo("pwResetPage");

        String pwInput = req.getParameter("password");
        String pwInputCheck = req.getParameter("passwordCheck");

        if (pwInput == null) {
            req.setAttribute("noticeMessage", "비밀번호와 비밀번호 체크를 입력해주세요 !!");
            return viewInfo;
        }

        if (!pwInput.equals(pwInputCheck)) {
            req.setAttribute("noticeMessage", "비밀번호와 비밀번호 체크가 같지 않습니다 !!");
            return viewInfo;
        }

        HttpSession session = req.getSession();
        PwCertificationInfo certificationInfo =
                (PwCertificationInfo) session.getAttribute("pwCertificationInfo");

        int accountNo = certificationInfo.getAccountNo();
        boolean success = this.findAccountService.resetPassword(accountNo, pwInput);

        if (!success) {
            req.setAttribute("noticeMessage", "오류입니다. 관리자에게 문의하세요..");
            return viewInfo;
        }

        session.setAttribute("pwCertificationInfo", null);
        viewInfo.setRedirectRequired();
        viewInfo.setViewName("pwResetCompletePage");
        return viewInfo;
    }

    @RequestMapping(uri = "/pwResetCompletePage", method = "get")
    public String showPwResetCompletePage(){
        return "pwResetCompletePage";
    }
}
