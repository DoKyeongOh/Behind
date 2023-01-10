package org.mytoypjt.service;

import lombok.AllArgsConstructor;
import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.exception.CustomException;
import org.mytoypjt.exception.ErrorCode;
import org.mytoypjt.models.entity.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountDao accountDao;

    public Account getAccount(String id, String pw) {
        List<Account> accounts = accountDao.findAccountByIdAndPw(id, pw);
        if (accounts == null || accounts.isEmpty()) {
            throw new CustomException(ErrorCode.ACCOUNT_IS_NOT_EXIST);
        }
        return accounts.get(0);
    }

    public List<Account> getAccountsByEmail(String email) {
        List<Account> accounts = accountDao.findByEmail(email);
        if (accounts == null || accounts.isEmpty()) {
            accounts = new ArrayList<>();
        }
        return accounts;
    }

    public int getAccountNo(String id, String email) {
        List<Account> accounts = accountDao.findByIdAndEmail(id, email);
        if (accounts == null || accounts.isEmpty()) {
            throw new CustomException(ErrorCode.ACCOUNT_IS_NOT_EXIST);
        }
        return accounts.get(0).getAccountNo();
    }

    public void setPassword(int accountNo, String password) {
        accountDao.setAccountPw(accountNo, password);
    }
}
