package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.dao.ProfileDao;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.service.annotation.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class LoginService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ProfileDao profileDao;

    public LoginService() {}

    public Profile getProfile(String accountId, String accountPw){
        int accountNo = accountDao.getAccountNo(accountId, accountPw);

        if (!Profile.isCorrectProfileNo(accountNo)) return null;
        Profile profile = profileDao.getProfile(accountNo);

        return profile;
    }
}
