package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.dao.ProfileDao;
import org.mytoypjt.models.dto.AccountCertDTO;
import org.mytoypjt.models.entity.Account;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ProfileDao profileDao;

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
        MailUtil mailUtil = new MailUtil();
        mailUtil.sendCertMail(account.getEmail(), certValue);
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
        if (profile.getNicname().length() > 10)
            return "닉네임은 10글자보다 짧아야 합니다!";
        if (profile.getNicname().contains(" "))
            return "닉네임에 공백은 사용할 수 없습니다!";
        try {
            if (!profileDao.updateProfile(profile))
                return "성공적으로 완수되지 못했습니다! 관리자에게 문의하세요!";
        } catch(Exception e) {
            return "예상치 못한 문제가 발생했습니다! 관리자에게 문의하세요!";
        }
        return "";
    }

    public boolean isCorrectPw(String pw, String pwCheck){
        if (pw == null || pwCheck == null) return false;
        if (!pw.equals(pwCheck)) return false;
        return true;
    }

    public CertErrorType getCertErrorType(AccountCertDTO dto, String inputValue){
        CertErrorType type = CertErrorType.isNull;
        if (dto == null)
            return CertErrorType.isNull;

        if (inputValue == null)
            return CertErrorType.notInput;

        if (!inputValue.equals(dto.getCertValue()))
            return CertErrorType.notSame;

        return CertErrorType.good;
    }

    public String getCertErrorMessage(CertErrorType selector){

        switch (selector) {
            case isNull : return "정보를 입력하고 이메일 인증을 먼저 시도해주세요 !!";

            case notInput : return "인증번호를 입력해주세요 !!";

            case notSame : return "인증번호가 일치하지 않습니다 !!";

            case good: return "";
        }

        return "예상치 못한 에러가 발생했습니다 관리자에게 문의해주세요 !!";
    }




}
