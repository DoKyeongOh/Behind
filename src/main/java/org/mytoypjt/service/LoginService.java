package org.mytoypjt.service;

import org.mytoypjt.consts.SessionConst;
import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.dao.LoginLogDao;
import org.mytoypjt.dao.ProfileDao;
import org.mytoypjt.exception.CustomException;
import org.mytoypjt.exception.ErrorCode;
import org.mytoypjt.models.dto.login.LoginRequestDTO;
import org.mytoypjt.models.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private LoginLogDao loginLogDao;

    Map<Integer, HttpSession> loginSessionMap;

    public LoginService() {
        loginSessionMap = new HashMap<>();
    }

    @Transactional
    public void login(LoginRequestDTO dto, HttpSession session) {
        int accountNo = accountDao.getAccountByIdAndPw(dto.getId(), dto.getPw()).getAccountNo();
        Profile profile = profileDao.getProfile(accountNo);
        addLoginSession(profile.getAccountNo(), session);
        session.setAttribute(SessionConst.USER_PROFILE, profile);
    }

    public void logout(int accountNo){
        loginLogDao.writeLog(accountNo, "로그아웃");
    public boolean addLoginSession(int accountNo, HttpSession session) {
        if (loginSessionMap.containsKey(accountNo)) {
            throw new CustomException(ErrorCode.ACCOUNT_IS_ALREADY_LOGIN);
        }

        loginSessionMap.put(accountNo, session);
        return true;
    }

    public void removeLoginSession(int accountNo){
        HttpSession session = loginSessionMap.get(accountNo);
        session.setAttribute(SessionConst.USER_PROFILE, null);
        loginSessionMap.remove(accountNo);
    }

    public void changeLoginSession(int accountNo, HttpSession session){
        loginSessionMap.replace(accountNo, session);
    }

    public boolean isProfileUsable(int accountNo, HttpSession session){
        if (session == null)
            return false;

        if (!loginSessionMap.get(accountNo).equals(session))
            return false;

        Profile profile = (Profile) session.getAttribute(SessionConst.USER_PROFILE);
        if (profile == null)
            return false;

        if (!loginSessionMap.get(accountNo).getAttribute(SessionConst.USER_PROFILE).equals(profile))
            return false;

        return true;
    }
}
