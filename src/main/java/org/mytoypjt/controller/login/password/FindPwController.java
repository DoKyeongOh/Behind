package org.mytoypjt.controller.login.password;

import org.mytoypjt.controller.structure.properties.PropertiesControllerTemplete;
import org.mytoypjt.models.dto.PwCertificationInfo;
import org.mytoypjt.models.etc.ViewInfo;

import javax.servlet.http.HttpSession;

public class FindPwController extends PropertiesControllerTemplete {

    @Override
    public Object doGet() {
        if (isUserExist())
            return "main";

        HttpSession session = req.getSession();
        session.setAttribute("pwCertificationInfo", null);

        return "findPw";
    }

    @Override
    public Object doPost() {

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
