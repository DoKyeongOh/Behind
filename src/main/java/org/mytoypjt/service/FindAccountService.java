package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.models.dto.IdCertificationInfo;
import org.mytoypjt.models.dto.PwCertificationInfo;
import org.mytoypjt.service.annotation.Transaction;
import org.mytoypjt.utils.MailUtil;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
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

    @Transaction
    public boolean isRegisteredEmail(String email){
        return accountDao.isRegisteredEmail(email);
    }

    @Transaction
    public List<String> getAccountListByEmail(String email){
        return accountDao.getAccountListByEmail(email);
    }

    @Transaction
    public int getAccountNo(String id, String email){
        int accountNo = accountDao.findAccountNo(id, email);
        return accountNo;
    }

    public boolean isCorrectAccountNo(int no){
        if (no < 0) return false;
        return true;
    }

    @Transaction
    public boolean resetPassword(int accountNo, String password){
        return accountDao.setAccountPw(accountNo, password);
    }
}
