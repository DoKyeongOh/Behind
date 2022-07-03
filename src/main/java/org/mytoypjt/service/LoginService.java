package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.dao.UserDao;
import org.mytoypjt.models.entity.User;

public class LoginService {
    private AccountDao loginDao;

    public LoginService() {

    }

    public User getUser(String userId, String userPw){
        AccountDao loginDao = new AccountDao();
        int accountNo = loginDao.getAccountNo(userId, userPw);

        if (!User.isCorrectUserNo(accountNo)) return null;

        UserDao userDao = new UserDao();
        User user = userDao.getUser(accountNo);

        return user;
    }
}
