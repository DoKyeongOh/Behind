package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.dao.UserDao;
import org.mytoypjt.models.dto.AccountCertDTO;
import org.mytoypjt.models.entity.Account;
import org.mytoypjt.models.entity.User;
import org.mytoypjt.utils.MailUtil;

public class RegisterService {

    AccountDao accountDao;
    UserDao userDao;

    public enum CertErrorType {
        isNull, notInput, notSame, good
    }

    public RegisterService() {
        accountDao = new AccountDao();
        userDao = new UserDao();
    }

    public boolean isUsableAccountId(String id){
        boolean isExistId = accountDao.isExistId(id);
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

    public boolean createAccount(Account account){
        boolean successed = accountDao.createAccount(account);
        return successed;
    }

    public boolean createDefaultUser(Account account){
        int accountNo = accountDao.findAccountNo(account);
        User user = new User(accountNo);
        boolean successed = userDao.createUser(user);
        return successed;
    }

    public boolean updateUser(User user) {
        return userDao.updateUser(user);
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
