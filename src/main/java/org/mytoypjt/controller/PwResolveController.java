package org.mytoypjt.controller;

import org.mytoypjt.models.dto.PwCertificationInfo;
import org.mytoypjt.service.FindAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class PwResolveController {
    @Autowired
    private FindAccountService findAccountService;

    public PwResolveController(){}

    @GetMapping(path = "/pw/page/{pageNo}")
    public ModelAndView showPage(@PathVariable("pageNo") int pageNo, HttpSession session){

        ModelAndView mv = new ModelAndView("findPwPage");
        switch (pageNo) {
            case 1: {
                session.setAttribute("pwCertificationInfo", null);
                mv.setViewName("findPwPage");
                return mv;
            }
            case 2: {
                PwCertificationInfo certificationInfo =
                        (PwCertificationInfo) session.getAttribute("pwCertificationInfo");
                if (certificationInfo == null) {
                    mv.addObject("noticeMessage", "인증을 먼저 진행해주세요 !!");
                    mv.setViewName("findPwPage");
                    return mv;
                }
                mv.setViewName("pwResetPage");
                return mv;
            }
            case 3: {
                mv.setViewName("pwResetCompletePage");
                return mv;
            }
            default: return mv;
        }
    }

    @GetMapping(path = "/pw/cert")
    public ModelAndView createPwCertification(@RequestParam Map<String, String> param, HttpSession session){
        String id = param.get("id");
        String email = param.get("email");
        String domain = param.get("domain");
        String emailAddress = email + "@" + domain;

        ModelAndView mv = new ModelAndView("findPwPage");

        PwCertificationInfo certificationInfo = findAccountService.getPwCertification(id, emailAddress);
        if (certificationInfo == null) {
            mv.addObject("noticeMessage", "입력 정보가 잘못되었습니다 !!");
            return mv;
        }

        session.setAttribute("pwCertificationInfo", certificationInfo);
        mv.addObject("noticeMessage", "이메일을 확인해주세요 !!");
        return mv;
    }

    @PostMapping(path = "/pw/cert")
    public ModelAndView checkPwCertification(HttpSession session,
                                         @RequestParam(name = "pwCertificationInput")String inputValue){
        PwCertificationInfo certificationInfo =
                (PwCertificationInfo) session.getAttribute("pwCertificationInfo");

        ModelAndView mv = new ModelAndView("findPwPage");

        if (certificationInfo == null) {
            mv.addObject("noticeMessage", "인증을 재시도해주세요 !!");
            return mv;
        }

        if (!certificationInfo.getCertificationValue().equals(inputValue)) {
            mv.addObject("noticeMessage", "인증번호를 확인 후 재시도해주세요 !!");
            return mv;
        }

        mv.setView(new RedirectView("/pw/page/2"));
        return mv;
    }

    @PutMapping(path = "/pw")
    public ModelAndView resetAccountPw(@RequestParam Map<String, String> param, HttpSession session){
        ModelAndView mv = new ModelAndView("pwResetPage");

        String pwInput = param.get("password");
        String pwInputCheck = param.get("passwordCheck");

        if (pwInput == null) {
            mv.addObject("noticeMessage", "비밀번호와 비밀번호 체크를 입력해주세요 !!");
            return mv;
        }

        if (!pwInput.equals(pwInputCheck)) {
            mv.addObject("noticeMessage", "비밀번호와 비밀번호 체크가 같지 않습니다 !!");
            return mv;
        }

        PwCertificationInfo certificationInfo =
                (PwCertificationInfo) session.getAttribute("pwCertificationInfo");

        int accountNo = certificationInfo.getAccountNo();
        boolean success = findAccountService.resetPassword(accountNo, pwInput);

        if (!success) {
            mv.addObject("noticeMessage", "오류입니다. 관리자에게 문의하세요..");
            return mv;
        }

        session.setAttribute("pwCertificationInfo", null);
        mv.setView(new RedirectView("/pw/page/3"));
        return mv;
    }


}
