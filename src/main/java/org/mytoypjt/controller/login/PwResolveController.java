package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.models.dto.PwCertificationInfo;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.service.FindAccountService;
import org.mytoypjt.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PwResolveController {

    public PwResolveController(){
    }

    @RequestMapping(uri = "/pw/page/1", method = "get")
    public ViewInfo getFindPwPage(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        session.setAttribute("pwCertificationInfo", null);

        if (ControllerUtils.isExistProfileSession(req))
            return ViewInfo.getRedirectViewInfo("/main/page");

        return new ViewInfo("findPwPage");
    }

    @RequestMapping(uri = "/pw/page/2", method = "get")
    public String getPwResetPage(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        PwCertificationInfo certificationInfo =
                (PwCertificationInfo) session.getAttribute("pwCertificationInfo");

        if (certificationInfo == null) {
            req.setAttribute("noticeMessage", "인증을 먼저 진행해주세요 !!");
            return "findPwPage";
        }

        return "pwResetPage";
    }

    @RequestMapping(uri = "/pw/page/3", method = "get")
    public String getPwResetCompletePage(HttpServletRequest req, HttpServletResponse resp){
        return "pwResetCompletePage";
    }

    @RequestMapping(uri = "/pw/cert", method="get")
    public String createPwCertification(HttpServletRequest req, HttpServletResponse resp){
        String id = (String) req.getParameter("id");
        String email = (String) req.getParameter("email");
        String domain = (String) req.getParameter("domain");
        String emailAddress = email + "@" + domain;

        FindAccountService findAccountService = new FindAccountService();
        PwCertificationInfo certificationInfo = findAccountService.getPwCertification(id, emailAddress);
        if (certificationInfo == null) {
            req.setAttribute("noticeMessage", "입력 정보가 잘못되었습니다 !!");
            return "findPwPage";
        }

        req.setAttribute("noticeMessage", "이메일을 확인해주세요 !!");

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("pwCertificationInfo", certificationInfo);
        return "findPwPage";
    }

    @RequestMapping(uri = "/pw/cert", method = "post")
    public ViewInfo checkPwCertification(HttpServletRequest req, HttpServletResponse resp){
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

        viewInfo.setRedirectTo("/pw/page/2");
        return viewInfo;
    }


    @RequestMapping(uri = "/pw", method = "put")
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

        FindAccountService findAccountService = new FindAccountService();
        int accountNo = certificationInfo.getAccountNo();
        boolean success = findAccountService.resetPassword(accountNo, pwInput);

        if (!success) {
            req.setAttribute("noticeMessage", "오류입니다. 관리자에게 문의하세요..");
            return viewInfo;
        }

        session.setAttribute("pwCertificationInfo", null);
        viewInfo.setRedirectRequire(true);
        viewInfo.setViewName("/pw/page/3");
        return viewInfo;
    }


}
