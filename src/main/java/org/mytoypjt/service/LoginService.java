package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.dao.ProfileDao;
import org.mytoypjt.models.entity.Profile;

public class LoginService {

    public LoginService() {

    }

    public Profile getProfile(String accountId, String accountPw){
        AccountDao loginDao = new AccountDao();
        int accountNo = loginDao.getAccountNo(accountId, accountPw);

        if (!Profile.isCorrectProfileNo(accountNo)) return null;

        ProfileDao profileDao = new ProfileDao();
        Profile profile = profileDao.getProfile(accountNo);

        return profile;
    }
}
