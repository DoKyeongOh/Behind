package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.exception.CustomException;
import org.mytoypjt.exception.ErrorCode;
import org.mytoypjt.models.dto.RegistrationRequestDTO;
import org.mytoypjt.models.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
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
        if (accounts == null) {
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

    public int createAccount(RegistrationRequestDTO dto) {
        try {
            return accountDao.createAccount(new Account(dto));
        } catch (Exception e) {
            throw new CustomException(ErrorCode.ACCOUNT_CREATION_FAILURE, e.getMessage());
        }
    }

    public void setPassword(int accountNo, String password) {
        accountDao.setAccountPw(accountNo, password);
    }

    public boolean isIdUsing(String id) {
        return accountDao.isIdUsing(id);
    }
}
