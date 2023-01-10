package org.mytoypjt.service;

import org.mytoypjt.models.dto.IdCertificationInfo;
import org.mytoypjt.models.dto.PwCertificationInfo;
import org.mytoypjt.models.entity.Account;
import org.mytoypjt.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountFindService {

    @Autowired
    private AccountService accountService;

    public AccountFindService() {

    }

    public IdCertDTO createIdCert(String email){
        List<Account> accounts = accountService.getAccountsByEmail(email);
        if (accounts.isEmpty()) {
            throw new CustomException(ErrorCode.EMAIL_IS_NOT_EXIST);
        }
        String certValue = getRandomValue();
        mailService.sendCertMail(email, certValue);
        return new IdCertDTO(certValue, email);
    }

        return new IdCertificationInfo(certificationValue, email);
    }

    public PwCertificationInfo getPwCertification(String id, String email){
        int accountNo = accountService.getAccountNo(id, email);
        String certificationValue = getRandomValue();
        new MailUtil().sendCertMail(email, certificationValue);
        return new PwCertificationInfo(accountNo, certificationValue);
    }

    public String getRandomValue(){
        String value = Integer.toString((int)(Math.random() * 1000000));
        return value;
    }

    public List<Account> getAccountsByEmail(String email){
        return accountService.getAccountsByEmail(email);
    }

    public void resetPassword(int accountNo, String password){
        accountService.setPassword(accountNo, password);
    }
}
