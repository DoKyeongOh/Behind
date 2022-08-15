package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.dao.ProfileDao;
import org.mytoypjt.models.dto.IdCertificationInfo;
import org.mytoypjt.models.dto.PwCertificationInfo;
import org.mytoypjt.utils.MailUtil;

import java.sql.Connection;
import java.util.List;

public class FindAccountService {

    private Connection connection;

    private AccountDao accountDao;

    public FindAccountService(){

    }

    public IdCertificationInfo getIdCertification(String email){
        if (!isRegisteredEmail(email))
            return null;

        String certificationValue = getRandomValue();
        new MailUtil().sendCertMail(email, certificationValue);

        return new IdCertificationInfo(certificationValue, email);
    }

    public PwCertificationInfo getPwCertification(String id, String email){
        int accountNo = getAccountNo(id, email);
        if (!isCorrectAccountNo(accountNo))
            return null;

        String certificationValue = getRandomValue();
        new MailUtil().sendCertMail(email, certificationValue);

        return new PwCertificationInfo(accountNo, certificationValue);
    }

    public String getRandomValue(){
        String value = Integer.toString((int)(Math.random() * 1000000));
        return value;
    }

    public boolean isRegisteredEmail(String email){
        AccountDao accountDao = new AccountDao();
        return accountDao.isRegisteredEmail(email);
    }

    public List<String> getAccountListByEmail(String email){
        AccountDao accountDao = new AccountDao();
        return accountDao.getAccountListByEmail(email);
    }

    public int getAccountNo(String id, String email){
        AccountDao accountDao = new AccountDao();
        int accountNo = accountDao.findAccountNo(id, email);
        return accountNo;
    }

    public boolean isCorrectAccountNo(int no){
        if (no < 0) return false;
        return true;
    }

    public boolean resetPassword(int accountNo, String password){
        AccountDao accountDao = new AccountDao();
        return accountDao.setAccountPw(accountNo, password);
    }
}
