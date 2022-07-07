package org.mytoypjt.controller.login.password;

import org.mytoypjt.controller.structure.PropertiesControllerTemplete;
import org.mytoypjt.models.dto.PwCertificationInfo;
import org.mytoypjt.service.FindAccountService;

import javax.servlet.http.HttpSession;

public class PwCertificationController extends PropertiesControllerTemplete {
    @Override
    public Object doPost() {
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
}
