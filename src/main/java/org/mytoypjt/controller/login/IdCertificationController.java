package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.ControllerTemplete;
import org.mytoypjt.models.dto.IdCertificationInfo;
import org.mytoypjt.service.FindAccountService;

import javax.servlet.http.HttpSession;

public class IdCertificationController extends ControllerTemplete {
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
        IdCertificationInfo certificationInfo = findAccountService.sendMailForFindId(emailAddr);
        if (certificationInfo.equals(null)) {
            req.setAttribute("userIdMessage", "이메일 정보가 잘못되었습니다 !!");
            return "findId";
        }

        req.setAttribute("userIdMessage", "이메일을 확인해주세요 !!");

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("idCertificationInfo", certificationInfo);
        return "findId";
    }
}
