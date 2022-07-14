package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.dao.ProfileDao;
import org.mytoypjt.models.dto.AccountCertDTO;
import org.mytoypjt.models.entity.Account;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.MailUtil;

public class RegisterService {

    AccountDao accountDao;
    ProfileDao profileDao;

    public enum CertErrorType {
        isNull, notInput, notSame, good
    }

    public RegisterService() {
        accountDao = new AccountDao();
        profileDao = new ProfileDao();
    }

    public boolean isUsableAccountNo(String no){
        boolean isExistId = accountDao.isExistId(no);
        return !isExistId;
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

    public boolean createAccount(AccountCertDTO certDTO){
        Account account = certDTO.getAccount();
        boolean accountOk = accountDao.createAccount(account);
        boolean profileOk = true;
        if (accountOk)
            profileOk = createDefaultProfile(account);

        if (!profileOk) {
            int accountNo = accountDao.findAccountNo(account);
            accountDao.deleteAccount(accountNo);
        }

        return profileOk;
    }

    public boolean createDefaultProfile(Account account){
        int accountNo = accountDao.findAccountNo(account);
        Profile profile = new Profile(accountNo);
        boolean successed = profileDao.createProfile(profile);
        return successed;
    }

    public int getCreatedAccountNo(AccountCertDTO certDTO){
        return accountDao.findAccountNo(certDTO.getAccount());
    }

    public boolean updateProfile(Profile profile) {
        return profileDao.updateProfile(profile);
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
