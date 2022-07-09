package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.models.dto.IdCertificationInfo;
import org.mytoypjt.service.FindAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IdResolveController {

    @RequestMapping(uri = "/findIdPage", method = "get")
    public String showFindIdPage(){
        return "findIdPage";
    }

    @RequestMapping(uri = "/idCertification", method = "get")
    public String createIdCertification(HttpServletRequest req, HttpServletResponse resp){
        String email = (String) req.getParameter("email");
        String domain = (String) req.getParameter("domain");
        String mailAddress = email + "@" + domain;

        FindAccountService findAccountService = new FindAccountService();
        IdCertificationInfo certificationInfo = findAccountService.getIdCertification(mailAddress);

        if (certificationInfo.equals(null)) {
            req.setAttribute("noticeMessage", "이메일 정보가 잘못되었습니다 !!");
            return ("findIdPage");
        }

        req.setAttribute("noticeMessage", "이메일을 확인해주세요 !!");

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("idCertificationInfo", certificationInfo);

        return "findIdPage";
    }

    @RequestMapping(uri = "/idCertification", method = "post")
    public String removeIdCertification(HttpServletRequest req, HttpServletResponse resp){
        HttpSession httpSession = req.getSession();

        IdCertificationInfo certificationInfo =
                (IdCertificationInfo) httpSession.getAttribute("idCertificationInfo");
        if (certificationInfo == null) {
            req.setAttribute("noticeMessage", "이메일을 입력해주세요 !!");
            return "findIdPage";
        }

        String inputValue = (String) req.getParameter("idCertificationInput");
        if (inputValue == null) {
            req.setAttribute("noticeMessage", "인증번호가 잘못되었습니다 !!");
            return "findIdPage";
        }

        if (!inputValue.equals(certificationInfo.getCertificationValue())){
            req.setAttribute("noticeMessage", "인증번호가 잘못되었습니다 !!");
            return "findIdPage";
        }

        FindAccountService findAccountService = new FindAccountService();
        String accountId = findAccountService.getAccountNoByEmail(certificationInfo.getEmailAddress());
        httpSession.setAttribute("idCertificationInfo", null);

        req.setAttribute("noticeMessage", "당신의 아이디는 '" + accountId + "' 입니다 !!");
        return "findIdPage";
    }

}
