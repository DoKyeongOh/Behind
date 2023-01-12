package org.mytoypjt.controller;

import org.mytoypjt.consts.SessionConst;
import org.mytoypjt.models.dto.cert.RegistrationCertDTO;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.dto.RegistrationRequestDTO;
import org.mytoypjt.service.AccountService;
import org.mytoypjt.service.LoginService;
import org.mytoypjt.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;

    final String ACCOUNT_CERT_KEY = "accountCert";

    public RegisterController(){}


    @GetMapping("/register/page/1")
    public String showRegisterPage() {
        return "registerPage";
    }

    @GetMapping(path = "/register/page/2")
    public ModelAndView showAccountInputPage(@RequestParam(required = false) String agreement){
        ModelAndView mv = new ModelAndView("registerPage");
        if (agreement != null) {
            mv.setViewName("accountInputPage");
            return mv;
        }
        mv.addObject("noticeMessage", "약관에 동의해주세요!!");
        return mv;
    }

    @GetMapping("/register/page/3")
    public String showProfileInputPage(){
        return "profileInputPage";
    }

    @ResponseBody
    @PostMapping(path = "/id-usage")
    public Map<String, Boolean> checkSameId(@RequestBody Map<String, String> body){
        Map<String, Boolean> resMap = new HashMap<>();
        if (accountService.isIdUsing(body.get("id")))
            resMap.put("usable", true);
        return resMap;
    }

    @PostMapping(path = "/account/cert")
    public ModelAndView createRegistrationCert(HttpSession session, RegistrationRequestDTO registrationRequestDTO) {
        session.setAttribute(
                SessionConst.REGISTRATION_CERT,
                registerService.createRegistrationCertDTO(registrationRequestDTO)
        );

        return new ModelAndView("accountInputPage")
                .addObject("noticeMessage", "이메일에서 인증번호 확인 후 인증번호를 입력해주세요");
    }


        session.setAttribute(ACCOUNT_CERT_KEY, null);
        session.setAttribute(SessionConst.USER_PROFILE, profile);
        loginService.addLoginSession(profile.getAccountNo(), session);

        mv.setView(new RedirectView("/register/page/3"));
        return mv;


    }

    @PostMapping(path = "/profile")
    public ModelAndView entryProfile(@RequestParam Map<String, String> param,
                                     @SessionAttribute("profile") Profile inputProfile,
                                     HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.setView(new RedirectView("/"));

        Profile newProfile = new Profile(
                inputProfile.getAccountNo(),
                param.get("nicname"),
                inputProfile.getRegisterDate(),
                param.get("city"),
                Integer.parseInt(param.get("age")),
                param.get("gender"),
                1);

        String msg = "";
        try {
            msg = registerService.updateProfile(newProfile);
        } catch(Exception e) {
            msg = "예상치 못한 문제가 발생했습니다! 관리자에게 문의하세요!";
            e.printStackTrace();
        }

        if (!msg.equals("")) {
            mv.addObject("noticeMessage", msg);
            mv.setViewName("profileInputPage");
            return mv;
        }

        inputProfile.injectProfile(newProfile);
        session.setAttribute(SessionConst.USER_PROFILE, inputProfile);

        return mv;
    }
}
