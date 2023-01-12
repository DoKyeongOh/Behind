package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.dao.ProfileDao;
import org.mytoypjt.models.dto.AccountCertDTO;
import org.mytoypjt.models.entity.Account;
import org.mytoypjt.models.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    MailService mailService;

    public enum CertErrorType {
        isNull, notInput, notSame, good
    }

    public RegisterService() {
    }

    public boolean isUsableAccountId(String id) {
        try {
            return !accountDao.isExistId(id);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AccountCertDTO sendAccountCert(Account account){
        String certValue = getRandomValue().trim();
        mailService.sendCertMail(account.getEmail(), certValue);
        return new AccountCertDTO(account, certValue);
    }


    public String getRandomValue(){
        String value = Integer.toString((int)(Math.random() * 1000000));
        return value;
    }

    @Transactional
    public Profile createAccount(AccountCertDTO certDTO) throws Exception {
        Account account = certDTO.getAccount();
        int accountNo = accountDao.createAccount(account);
        if (!Account.isCorrectAccountNo(accountNo))
            return null;

        Profile profile = new Profile(accountNo);
        profileDao.createProfile(profile);
        return profile;
    }

    public String updateProfile(Profile profile) {
        if (profile.getNickname().length() > 10)
            return "닉네임은 10글자보다 짧아야 합니다!";
        if (profile.getNickname().contains(" "))
            return "닉네임에 공백은 사용할 수 없습니다!";
        if (!profileDao.updateProfile(profile))
            return "성공적으로 완수되지 못했습니다! 관리자에게 문의하세요!";
        return "";
    }

    public boolean isCorrectPw(String pw, String pwCheck){
        if (pw == null || pwCheck == null) return false;
        if (!pw.equals(pwCheck)) return false;
        return true;
    }



}
