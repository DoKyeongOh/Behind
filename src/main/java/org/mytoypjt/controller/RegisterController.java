package org.mytoypjt.controller;

import org.mytoypjt.controller.consts.SessionConst;
import org.mytoypjt.models.dto.AccountCertDTO;
import org.mytoypjt.models.entity.Account;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.vo.RegistVO;
import org.mytoypjt.service.RegisterService;
import org.mytoypjt.utils.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    LoginManager loginManager;

    final String ACCOUNT_CERT_KEY = "accountCert";

    public RegisterController(){}

    @GetMapping(path = "/register/page/{pageNo}")
    public ModelAndView ShowRegisterPages(@PathVariable(name = "pageNo")int pageNo,
                                          @RequestParam Map<String, String> param){
        ModelAndView mv = new ModelAndView("registerPage");
        switch (pageNo) {
            case 1:
                return mv;
            case 2: {
                String agree = param.get("isAgree");
                if (agree == null || !agree.equals("agree")) {
                    mv.addObject("noticeMessage", "약관에 동의해주세요!!");
                    return mv;
                }
                mv.setViewName("accountInputPage");
                return mv;
            }
            case 3: {
                mv.setViewName("profileInputPage");
            }
            default: return mv;
        }
    }


    @PostMapping(path = "/account")
    public ModelAndView entryAccount(Model model, HttpSession session, @ModelAttribute RegistVO registVO){
        ModelAndView modelAndView = new ModelAndView("accountInputPage");

        boolean isUsableId = registerService.isUsableAccountId(registVO.getId());
        if (!isUsableId) {
            model.addAttribute("noticeMessage", "아이디를 이미 사용중입니다 !!");
            return modelAndView;
        }

        if (!registerService.isCorrectPw(registVO.getPw(), registVO.getPwCheck())) {
            model.addAttribute("noticeMessage", "비밀번호를 확인해주세요 !!");
            return modelAndView;
        }

        String rcvMailAddress = registVO.getEmailAddress();
        Account account = new Account(registVO.getId(), registVO.getPw(), rcvMailAddress);

        AccountCertDTO dto = registerService.sendAccountCert(account);
        dto.setAccount(account);

        session.setAttribute(ACCOUNT_CERT_KEY, dto);

        model.addAttribute("noticeMessage", "이메일에서 인증번호 확인 후 인증번호를 입력해주세요");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping(path = "/id-usage")
    public Map<String, Boolean> checkSameId(@RequestBody Map<String, String> param){
        String newId = "";
        Map<String, Boolean> resMap = new HashMap<>();

        if (param.containsKey("id"))
            newId = (String) param.get("id");

        if (registerService.isUsableAccountId(newId))
            resMap.put("usable", true);

        return resMap;
    }

    @PostMapping(path = "/account/cert")
    public ModelAndView checkAccountCert(HttpSession session, @RequestParam Map<String, String> param){
        AccountCertDTO dto = (AccountCertDTO) session.getAttribute(ACCOUNT_CERT_KEY);
        String inputValue = param.get("accountCertInput");

        RegisterService.CertErrorType type = registerService.getCertErrorType(dto, inputValue);
        String errorMessage = registerService.getCertErrorMessage(type);

        ModelAndView mv = new ModelAndView("accountInputPage");

        if (!errorMessage.equals("")) {
            mv.addObject("noticeMessage", errorMessage);
            return mv;
        }

        Profile profile = null;
        try {
            profile = registerService.createAccount(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (profile == null) {
            String errorKey = "noticeMessage";
            String errorValue = "예상치 못한 오류가 발생했습니다 다시 시도해주세요.";
            mv.addObject(errorKey, errorValue);
            return mv;
        }

        session.setAttribute(ACCOUNT_CERT_KEY, null);
        session.setAttribute(SessionConst.userProfile, profile);
        loginManager.addLoginSession(profile.getAccountNo(), session);

        mv.setView(new RedirectView("/register/page/3"));
        return mv;


    }

    @PostMapping(path = "/profile")
    public ModelAndView entryProfile(@RequestParam Map<String, String> param,
                                     @ModelAttribute Profile inputProfile,
                                     @SessionAttribute("profile") Profile profile,
                                     HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.setView(new RedirectView("/"));

        profile.setNicname(inputProfile.getNicname());
        profile.setAge(inputProfile.getAge());
        profile.setCity(inputProfile.getCity());
        profile.setGender(inputProfile.getGender());

        session.setAttribute(SessionConst.userProfile, profile);
        boolean successed = registerService.updateProfile(profile);

        if (!successed) {
            mv.addObject("noticeMessage", "예상치 못한 문제가 발생했습니다. 관리자에게 문의하세요!");
            return mv;
        }
        return mv;
    }
}
