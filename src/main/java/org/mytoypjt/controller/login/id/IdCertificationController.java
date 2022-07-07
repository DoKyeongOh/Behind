package org.mytoypjt.controller.login.id;

import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;
import org.mytoypjt.models.dto.IdCertificationInfo;
import org.mytoypjt.service.FindAccountService;

import javax.servlet.http.HttpSession;

public class IdCertificationController extends PropertiesControllerTemplete {
    @Override
    public Object doGet() {
        // 인증 번호를 달라.
        String email = (String) req.getParameter("email");
        String domain = (String) req.getParameter("domain");
        String emailAddr = email + "@" + domain;

        FindAccountService findAccountService = new FindAccountService();
        IdCertificationInfo certificationInfo = findAccountService.getIdCertification(emailAddr);
        if (certificationInfo.equals(null)) {
            req.setAttribute("noticeMessage", "이메일 정보가 잘못되었습니다 !!");
            return "findId";
        }

        req.setAttribute("noticeMessage", "이메일을 확인해주세요 !!");

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("idCertificationInfo", certificationInfo);
        return "findId";
    }
}
