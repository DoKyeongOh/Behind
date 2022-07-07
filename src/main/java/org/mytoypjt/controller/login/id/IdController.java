package org.mytoypjt.controller.login.id;

import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;
import org.mytoypjt.models.dto.IdCertificationInfo;
import org.mytoypjt.service.FindAccountService;

import javax.servlet.http.HttpSession;

public class IdController extends PropertiesControllerTemplete {

    @Override
    public Object doGet() {
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("idCertificationInfo", null);
        return "findId";
    }

    @Override
    public Object doPost() {
        String inputValue = (String) req.getParameter("certificationInput");

        HttpSession httpSession = req.getSession();
        IdCertificationInfo certificationInfo =
                (IdCertificationInfo) httpSession.getAttribute("idCertificationInfo");

        if (!inputValue.equals(certificationInfo.getCertificationValue())){
            req.setAttribute("noticeMessage", "인증번호가 잘못되었습니다 !!");
            return "findId";
        }

        FindAccountService findAccountService = new FindAccountService();
        String accountId = findAccountService.getAccountNoByEmail(certificationInfo.getEmailAddress());
        httpSession.setAttribute("idCertificationInfo", null);

        req.setAttribute("noticeMessage", "당신의 아이디는 '" + accountId + "' 입니다 !!");

        return "findId";
    }
}
