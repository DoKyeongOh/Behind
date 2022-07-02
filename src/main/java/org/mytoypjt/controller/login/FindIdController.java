package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.ControllerTemplete;
import org.mytoypjt.service.FindAccountService;

import javax.servlet.http.HttpSession;

public class FindIdController extends ControllerTemplete {

    @Override
    public Object executeGetRequest() {
        if (isUserExist())
            return "main";
        return "findId";
    }

    @Override
    public Object executePostRequest() {
        String inputValue = (String) req.getParameter("certificationInput");

        HttpSession httpSession = req.getSession();
        String certificationValue = (String) httpSession.getAttribute("certificationValue");
        String userMailAddr = (String) httpSession.getAttribute("userMailAddr");

        if (!inputValue.equals(certificationValue)){
            req.setAttribute("userIdMessage", "인증번호가 잘못되었습니다 !!");
            return "findId";
        }

        FindAccountService findAccountService = new FindAccountService();
        String UserId = findAccountService.getUserIdFromEmail(userMailAddr);
        req.setAttribute("userIdMessage", "당신의 아이디는 '" + UserId + "' 입니다 !!");

        httpSession.setAttribute("certificationInput", null);
        httpSession.setAttribute("certificationValue", null);
        httpSession.setAttribute("userMailAddr", null);

        return "findId";
    }
}
