package org.mytoypjt.controller;

import org.mytoypjt.models.dto.IdCertDTO;
import org.mytoypjt.models.dto.cert.IdCertRequestDTO;
import org.mytoypjt.service.AccountFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IdFindController {

    @Autowired
    private AccountFindService accountFindService;

    public IdFindController(){}

    @GetMapping(path = "/id/page")
    public String showFindIdPage(HttpSession session){
        session.setAttribute("idCert", null);
        return "findIdPage";
    }

    @PostMapping(path = "/id/cert")
    public ModelAndView createIdCert(IdCertRequestDTO reqDTO, HttpSession session){
        IdCertDTO certDTO = accountFindService.createIdCert(reqDTO.getEmailAddress());
        ModelAndView mv = new ModelAndView("findIdPage");
        mv.addObject("noticeMessage", "이메일을 확인해주세요 !!");
        session.setAttribute("idCert", certDTO);
        return mv;
    }

    @DeleteMapping(path = "/id/cert")
    public ModelAndView checkIdCert(HttpSession session, String certValue){
        List<String> ids = accountFindService.checkIdCert(
                (IdCertDTO) session.getAttribute("idCert"),
                certValue
        );

        session.setAttribute("idCert", null);
        String returnMessage = "해당 이메일로 가입되어있는 아이디는 아래와 같습니다!<br>";
        for (String id : ids) {
            returnMessage = returnMessage + id + "<br>";
        }

        ModelAndView mv = new ModelAndView("findIdPage");
        mv.addObject("noticeMessage", returnMessage);
        return mv;
    }

}
