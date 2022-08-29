package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.dao.ProfileDao;
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

    public LoginService() {}

    public Profile getProfile(String accountId, String accountPw){
        int accountNo = accountDao.getAccountNo(accountId, accountPw);

        if (!Profile.isCorrectProfileNo(accountNo)) return null;
        Profile profile = profileDao.getProfile(accountNo);

        return profile;
    }
}
