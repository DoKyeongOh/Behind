package org.mytoypjt.controller;

import org.mytoypjt.models.dto.PwCertDTO;
import org.mytoypjt.models.dto.cert.PwCertRequestDTO;
import org.mytoypjt.models.dto.cert.PwResetRequestDTO;
import org.mytoypjt.service.AccountFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class PwFindController {
    @Autowired
    private AccountFindService accountFindService;

    public PwFindController(){}

    @GetMapping("/pw/page/1")
    public String showFindPwPage(HttpSession session){
        session.setAttribute("pwCert", null);
        return "findPwPage";
    }

    @GetMapping(path = "/pw/page/2")
    public ModelAndView showPage(HttpSession session){
        ModelAndView mv = new ModelAndView("pwResetPage");
        if (session.getAttribute("pwCert") == null) {
            mv.addObject("noticeMessage", "인증을 먼저 진행해주세요 !!");
            mv.setViewName("findPwPage");
            return mv;
        }
        return mv;
    }

    @GetMapping("/pw/page/3")
    public String showFindPwCompletePage() {
        return "pwResetCompletePage";
    }

    @PostMapping(path = "/pw/cert")
    public ModelAndView createPwCert(PwCertRequestDTO dto, HttpSession session){
        ModelAndView mv = new ModelAndView("findPwPage");
        PwCertDTO certDTO = accountFindService.createPwCert(dto);
        session.setAttribute("pwCert", certDTO);
        mv.addObject("noticeMessage", "이메일을 확인해주세요 !!");
        return mv;
    }

    @DeleteMapping(path = "/pw/cert")
    public ModelAndView checkPwCert(@SessionAttribute("pwCert") PwCertDTO dto,
                                    @RequestParam String certValue){
        accountFindService.checkPwCert(dto, certValue);
        ModelAndView mv = new ModelAndView("findPwPage");
        mv.setView(new RedirectView("/pw/page/2"));
        return mv;
    }

    @PutMapping(path = "/pw")
    public ModelAndView resetAccountPw(PwResetRequestDTO pwResetDTO, HttpSession session){
        PwCertDTO certDTO = (PwCertDTO) session.getAttribute("pwCert");
        accountFindService.resetPassword(certDTO.getAccountNo(), pwResetDTO);
        session.setAttribute("pwCert", null);

        ModelAndView mv = new ModelAndView("pwResetPage");
        mv.setView(new RedirectView("/pw/page/3"));
        return mv;
    }


}
