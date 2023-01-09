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

    public LoginService() {}

    @Transactional
    public Profile getProfile(String accountId, String accountPw) throws Exception {
        int accountNo = accountDao.getAccountNo(accountId, accountPw);

        if (!Profile.isCorrectProfileNo(accountNo)) return null;
        Profile profile = profileDao.getProfile(accountNo);
        loginLogDao.writeLog(accountNo, "로그인");

        return profile;
    }

    public void logout(int accountNo){
        loginLogDao.writeLog(accountNo, "로그아웃");
    }
}
