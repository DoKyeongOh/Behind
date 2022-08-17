package org.mytoypjt.controller.register;

import org.json.simple.JSONObject;
import org.mytoypjt.models.dto.AccountCertDTO;
import org.mytoypjt.models.entity.Account;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.etc.ViewInfo;
import org.mytoypjt.models.vo.RegistVO;
import org.mytoypjt.service.RegisterService;
import org.mytoypjt.utils.ControllerUtils;
import org.mytoypjt.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    final String ACCOUNT_CERT_KEY = "accountCert";
    final String ACCOUNT_NO = "accountNo";

    public RegisterController(){}

    @GetMapping(path = "/register/page/1")
    public String showRegisterPage(){
        return "registerPage";
    }

    @GetMapping(path = "/register/page/2")
    public String showAccountInputPage(@RequestParam(name = "isAgree") String agree, Model model, HttpSession session){
        if (agree == null) {
            model.addAttribute("noticeMessage", "약관에 동의해주세요!!");
            return "registerPage";
        }
        if (!agree.equals("agree")) {
            model.addAttribute("noticeMessage", "약관에 동의해주세요!!");
            return "registerPage";
        }

        session.setAttribute(ACCOUNT_CERT_KEY, null);

        return "accountInputPage";
    }

    @GetMapping(path = "/register/page/3")
    public String showProfileInputPage(){
        return "profileInputPage";
    }

    @PostMapping(path = "/account")
    public ViewInfo entryAccount(Model model, HttpSession session, RegistVO registVO){
        ViewInfo viewInfo = new ViewInfo("accountInputPage");

        boolean isUsableId = registerService.isUsableAccountNo(registVO.getId());
        if (!isUsableId) {
            model.addAttribute("noticeMessage", "아이디를 이미 사용중입니다 !!");
            return viewInfo;
        }

        if (!registerService.isCorrectPw(registVO.getPw(), registVO.getPwCheck())) {
            model.addAttribute("noticeMessage", "비밀번호를 확인해주세요 !!");
            return viewInfo;
        }

        String rcvMailAddress = registVO.getEmailAddress();
        Account account = new Account(registVO.getId(), registVO.getPw(), rcvMailAddress);

        AccountCertDTO dto = registerService.sendAccountCert(account);
        dto.setAccount(account);

        session.setAttribute(ACCOUNT_CERT_KEY, dto);

        model.addAttribute("noticeMessage", "이메일에서 인증번호 확인 후 인증번호를 입력해주세요");
        return viewInfo;
    }


    @PostMapping(path = "/id-usage")
    public JSONObject checkSameId(@RequestBody Map<String, String> paramMap){
        String newId = "";
        if (paramMap.containsKey("id")) {
            newId = paramMap.get("id");
        }

        JSONObject respJsonObject = new JSONObject();
        RegisterService registerService = new RegisterService();

        boolean isUsable = registerService.isUsableAccountNo(newId);
        respJsonObject.put("isUsable", isUsable);

        return respJsonObject;
    }

    @PostMapping(path = "/account/cert")
    public ModelAndView checkAccountCert(HttpSession session, Model model){
        AccountCertDTO dto = (AccountCertDTO) session.getAttribute(ACCOUNT_CERT_KEY);
        String inputValue = (String) model.getAttribute("accountCertInput");

        RegisterService.CertErrorType type = registerService.getCertErrorType(dto, inputValue);
        String errorMessage = registerService.getCertErrorMessage(type);

        if (!errorMessage.equals("")) {
            model.addAttribute("noticeMessage", errorMessage);
            return new ModelAndView("accountInputPage");
        }

        boolean successed = registerService.createAccount(dto);
        if (!successed) {
            model.addAttribute("noticeMessage", "예상치 못한 오류가 발생했습니다 다시 시도해주세요.");
            return new ModelAndView("accountInputPage");
        }

        int accountNo = registerService.getCreatedAccountNo(dto);
        if (!registerService.isUsableAccountNo(Integer.toString(accountNo))) {
            model.addAttribute("noticeMessage", "예상치 못한 오류가 발생했습니다 다시 시도해주세요.");
            return new ModelAndView("accountInputPage");
        }

        session.setAttribute(ACCOUNT_CERT_KEY, null);
        session.setAttribute(ACCOUNT_NO, accountNo);

        ModelAndView mv = new ModelAndView();
        mv.setView(new RedirectView("/register/page/3"));
        return mv;
    }

    @PostMapping(path = "/profile")
    public ModelAndView updateProfile(Model model, HttpSession session){
        String nicname = (String) model.getAttribute("nicname");
        String age = (String) model.getAttribute("age");
        String city = (String) model.getAttribute("city");
        String gender = (String) model.getAttribute("genderSelector");

        ModelAndView mv = new ModelAndView();
        mv.setView(new RedirectView("/"));

        int accountNo = 0;
        try {
            accountNo = (int) session.getAttribute(ACCOUNT_NO);
            session.setAttribute(ACCOUNT_NO, null);
        } catch (Exception e) {
            return mv;
        }

        RegisterService registerService = new RegisterService();
        Profile profile = new Profile(accountNo, nicname, new Date(), city, Integer.parseInt(age), gender, 1);
        boolean successed = registerService.updateProfile(profile);

        if (!successed) {
            model.addAttribute("noticeMessage", "예상치 못한 문제가 발생했습니다. 관리자에게 문의하세요!");
            return mv;
        }

        return mv;
    }
}
