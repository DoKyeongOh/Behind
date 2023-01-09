package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.dao.ProfileDao;
import org.mytoypjt.dao.LoginLogDao;
import org.mytoypjt.models.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private LoginLogDao loginLogDao;

    Map<Integer, HttpSession> loginSessionMap;

    @Transactional
    public Profile getProfile(String accountId, String accountPw) throws Exception {
        int accountNo = accountDao.getAccountNo(accountId, accountPw);
    public LoginService() {
        loginSessionMap = new HashMap<>();
    }

        if (!Profile.isCorrectProfileNo(accountNo)) return null;
        Profile profile = profileDao.getProfile(accountNo);
        loginLogDao.writeLog(accountNo, "로그인");

        return profile;
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
