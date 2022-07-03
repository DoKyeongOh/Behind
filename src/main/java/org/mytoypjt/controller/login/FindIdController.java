package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.ControllerTemplete;
import org.mytoypjt.models.dto.IdCertificationInfo;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.service.FindAccountService;

import javax.servlet.http.HttpSession;

public class FindIdController extends ControllerTemplete {

    @Override
    public Object executeGetRequest() {
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("idCertificationInfo", null);
        return "findId";
    }

    @Override
    public Object executePostRequest() {
        String inputValue = (String) req.getParameter("certificationInput");

        HttpSession httpSession = req.getSession();
        IdCertificationInfo certificationInfo =
                (IdCertificationInfo) httpSession.getAttribute("idCertificationInfo");

        if (!inputValue.equals(certificationInfo.getCertificationValue())){
            req.setAttribute("userIdMessage", "인증번호가 잘못되었습니다 !!");
            return "findId";
        }

        FindAccountService findAccountService = new FindAccountService();
        String accountId = findAccountService.getAccountIdFromEmail(certificationInfo.getEmailAddress());
        httpSession.setAttribute("idCertificationInfo", null);

        req.setAttribute("userIdMessage", "당신의 아이디는 '" + accountId + "' 입니다 !!");

        return "findId";
    }
}
