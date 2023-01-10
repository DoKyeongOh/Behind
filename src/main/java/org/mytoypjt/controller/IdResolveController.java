package org.mytoypjt.controller;

import org.mytoypjt.models.dto.IdCertificationInfo;
import org.mytoypjt.models.entity.Account;
import org.mytoypjt.service.AccountFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class IdResolveController {

    @Autowired
    private AccountFindService accountFindService;

    public IdResolveController(){}

    @GetMapping(path = "/id/page")
    public String showFindIdPage(HttpSession session){
        session.setAttribute("idCertificationInfo", null);
        return "findIdPage";
    }

    @GetMapping(path = "/id/cert")
    public ModelAndView createIdCertification(@RequestParam Map<String, String> param, HttpSession session){
        String email = param.get("email");
        String domain = param.get("domain");
        String mailAddress = email + "@" + domain;

        IdCertificationInfo certificationInfo = accountFindService.getIdCertification(mailAddress);

        ModelAndView mv = new ModelAndView("findIdPage");

        if (certificationInfo == null) {
            mv.addObject("noticeMessage", "이메일 정보가 잘못되었습니다 !!");
            return mv;
        }

        mv.addObject("noticeMessage", "이메일을 확인해주세요 !!");

        session.setAttribute("idCertificationInfo", certificationInfo);

        return mv;
    }

    @PostMapping(path = "/id/cert")
    public ModelAndView removeIdCertification(@RequestParam Map<String, String> param, HttpSession session){
        ModelAndView mv = new ModelAndView("findIdPage");

        IdCertificationInfo certificationInfo =
                (IdCertificationInfo) session.getAttribute("idCertificationInfo");
        if (certificationInfo == null) {
            mv.addObject("noticeMessage", "이메일을 입력해주세요 !!");
            return mv;
        }

        String inputValue = param.get("idCertificationInput");
        if (inputValue == null) {
            mv.addObject("noticeMessage", "인증번호가 잘못되었습니다 !!");
            return mv;
        }

        if (!inputValue.equals(certificationInfo.getCertificationValue())){
            mv.addObject("noticeMessage", "인증번호가 잘못되었습니다 !!");
            return mv;
        }

        List<String> idList = accountFindService.getAccountsByEmail(certificationInfo.getEmailAddress())
                .stream()
                .map(Account::getId)
                .collect(Collectors.toList());
        if (idList == null){
            mv.addObject("noticeMessage", "오류가 발생했습니다 관리자에게 문의해주세요 !!");
            return mv;
        }

        session.setAttribute("idCertificationInfo", null);
        String returnMessage = "해당 이메일로 가입되어있는 아이디는 아래와 같습니다!<br>";
        for (String id : idList) {
            returnMessage = returnMessage + id + "<br>";
        }

        mv.addObject("noticeMessage", returnMessage);
        return mv;
    }

}
