package org.mytoypjt.controller.login;

import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.models.dto.IdCertificationInfo;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.service.FindAccountService;
import org.mytoypjt.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class IdResolveController {

    public IdResolveController(){
    }

    @RequestMapping(uri = "/id/page", method = "get")
    public ViewInfo showFindIdPage(HttpServletRequest req, HttpServletResponse resp){

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("idCertificationInfo", null);

        if (ControllerUtils.isExistProfileSession(req))
            return ViewInfo.getRedirectViewInfo("/main/page");

        return new ViewInfo("findIdPage");
    }

    @RequestMapping(uri = "/id/cert", method = "get")
    public String createIdCertification(HttpServletRequest req, HttpServletResponse resp){
        String email = (String) req.getParameter("email");
        String domain = (String) req.getParameter("domain");
        String mailAddress = email + "@" + domain;

        FindAccountService findAccountService = new FindAccountService();
        IdCertificationInfo certificationInfo = findAccountService.getIdCertification(mailAddress);

        if (certificationInfo == null) {
            req.setAttribute("noticeMessage", "이메일 정보가 잘못되었습니다 !!");
            return ("findIdPage");
        }

        req.setAttribute("noticeMessage", "이메일을 확인해주세요 !!");

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("idCertificationInfo", certificationInfo);

        return "findIdPage";
    }

    @RequestMapping(uri = "/id/cert", method = "post")
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
        List<String> idList = findAccountService.getAccountListByEmail(certificationInfo.getEmailAddress());
        if (idList == null){
            req.setAttribute("noticeMessage", "오류가 발생했습니다 관리자에게 문의해주세요 !!");
            return "findIdPage";
        }

        httpSession.setAttribute("idCertificationInfo", null);
        String returnMessage = "해당 이메일로 가입되어있는 아이디는 아래와 같습니다!<br>";
        for (String id : idList) {
            returnMessage = returnMessage + id + "<br>";
        }

        req.setAttribute("noticeMessage", returnMessage);
        return "findIdPage";
    }

}
