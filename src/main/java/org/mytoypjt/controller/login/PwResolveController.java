package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.models.dto.PwCertificationInfo;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.service.FindAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PwResolveController {

    @RequestMapping(uri = "/findPw", method = "get")
    public String showFindPwPage(){
        return "findPw";
    }

    @RequestMapping(uri = "/pwCertification", method="get")
    public String createPwCertification(HttpServletRequest req, HttpServletResponse resp){
        String id = (String) req.getParameter("id");
        String email = (String) req.getParameter("email");
        String domain = (String) req.getParameter("domain");
        String emailAddress = email + "@" + domain;

        FindAccountService findAccountService = new FindAccountService();
        PwCertificationInfo certificationInfo = findAccountService.getPwCertification(id, emailAddress);
        if (certificationInfo== null) {
            req.setAttribute("noticeMessage", "입력 정보가 잘못되었습니다 !!");
            return "findPw";
        }

        req.setAttribute("noticeMessage", "이메일을 확인해주세요 !!");

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("pwCertificationInfo", certificationInfo);
        return "findPw";
    }

    @RequestMapping(uri = "/pwCertification", method = "post")
    public ViewInfo removePwCertification(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        PwCertificationInfo certificationInfo =
                (PwCertificationInfo) session.getAttribute("pwCertificationInfo");
        String inputValue = (String) req.getParameter("pwCertificationInput");

        ViewInfo viewInfo = new ViewInfo("findPw");

        if (certificationInfo == null) {
            req.setAttribute("noticeMessage", "인증을 재시도해주세요 !!");
            return viewInfo;
        }

        if (!certificationInfo.getCertificationValue().equals(inputValue)) {
            req.setAttribute("noticeMessage", "인증번호를 확인 후 재시도해주세요 !!");
            return viewInfo;
        }

        viewInfo.setRedirectRequired();
        viewInfo.setViewName("pwReset");

        return viewInfo;
    }

}
