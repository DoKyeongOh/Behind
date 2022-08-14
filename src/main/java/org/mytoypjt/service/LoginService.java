package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.dao.ProfileDao;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.service.annotation.Transaction;

import java.sql.Connection;

public class LoginService {

    private Connection connection = null;

    public LoginService() {

    }

    @Transaction
    public Profile getProfile(String accountId, String accountPw){
        AccountDao accountDao = new AccountDao();
        int accountNo = accountDao.getAccountNo(accountId, accountPw);

        if (!Profile.isCorrectProfileNo(accountNo)) return null;

        ProfileDao profileDao = new ProfileDao();
        Profile profile = profileDao.getProfile(accountNo);

        return profile;
    }
}
