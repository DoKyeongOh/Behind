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

    public PwCertDTO createPwCert(PwCertRequestDTO dto){
        if (dto == null) {
            throw new CustomException(ErrorCode.CERT_VALUE_IS_NULL);
        }
        String certValue = getRandomValue();
        String email = dto.getEmailAddress();
        mailService.sendCertMail(email, certValue);

        int accountNo = accountService.getAccountNo(dto.getId(), email);
        return new PwCertDTO(certValue, accountNo);
    }

    public List<String> checkIdCert(IdCertDTO sessDTO, String certValue){
        if (sessDTO == null) {
            throw new CustomException(ErrorCode.CERT_VALUE_IS_NULL);
        }
        sessDTO.checkCertValue(certValue);
        return accountService.getAccountsByEmail(sessDTO.getEmail())
                .stream()
                .map(Account::getId)
                .collect(Collectors.toList());
    }

    public String getRandomValue(){
        String value = Integer.toString((int)(Math.random() * 1000000));
        return value;
    public void checkPwCert(PwCertDTO dto, String certValue) {
        if (dto == null) {
            throw new CustomException(ErrorCode.CERT_VALUE_IS_NULL);
        }
        if (!dto.getCertValue().equals(certValue)) {
            throw new CustomException(ErrorCode.CERT_VALUE_IS_NOT_CORRECT);
        }
        dto.checkCertValue(certValue);
    }

    public void resetPassword(int accountNo, PwResetRequestDTO dto) {
        dto.checkValue();
        accountService.setPassword(accountNo, dto.getPassword());
    }

    }
}
