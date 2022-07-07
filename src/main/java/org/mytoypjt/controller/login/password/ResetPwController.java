package org.mytoypjt.controller.login.password;

import org.mytoypjt.controller.structure.PropertiesControllerTemplete;
import org.mytoypjt.models.dto.PwCertificationInfo;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.service.FindAccountService;

import javax.servlet.http.HttpSession;

public class ResetPwController extends PropertiesControllerTemplete {

    @Override
    public Object doGet() {
        return "pwReset";
    }

    @Override
    public Object doPost() {
        HttpSession session = req.getSession();
        PwCertificationInfo certificationInfo =
                (PwCertificationInfo) session.getAttribute("pwCertificationInfo");
        int accountNo = certificationInfo.getAccountNo();

        String inputPw = req.getParameter("password");
        String inputPwCheck = req.getParameter("passwordCheck");

        ViewInfo viewInfo = new ViewInfo("pwReset");

        if (!inputPw.equals(inputPwCheck)) {
            req.setAttribute("isCorrect", "'비밀번호'와 '비밀번호 확인'이 동일하지 않습니다 !!");
            return viewInfo;
        }

        FindAccountService findAccountService = new FindAccountService();
        boolean isFinished = findAccountService.resetPassword(accountNo, inputPw);

        if (isFinished){
            viewInfo.setViewName("pwResetComplete");
            viewInfo.setRedirectRequired();
            return viewInfo;
        }

        return viewInfo;
    }
}
