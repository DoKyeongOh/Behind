package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.ControllerTemplete;
import org.mytoypjt.service.FindAccountService;

import javax.servlet.http.HttpSession;

public class EmailCertificationController extends ControllerTemplete {
    @Override
    public Object executeGetRequest() {
        return "findId";
    }

    @Override
    public Object executePostRequest() {
        String email = (String) req.getParameter("email");
        String domain = (String) req.getParameter("domain");
        String emailAddr = email + "@" + domain;

        FindAccountService findAccountService = new FindAccountService();
        boolean isMailed = findAccountService.sendMailForFindId(emailAddr);
        if (!isMailed) {
            req.setAttribute("userIdMessage", "이메일 정보가 잘못되었습니다 !!");
            return "findId";
        }

        req.setAttribute("userIdMessage", "이메일을 확인해주세요 !!");

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("certificationValue", findAccountService.getCertificationValue());
        httpSession.setAttribute("userMailAddr", emailAddr);
        return "findId";
    }
}
