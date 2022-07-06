package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.models.dto.IdCertificationInfo;
import org.mytoypjt.models.dto.PwCertificationInfo;
import org.mytoypjt.utils.MailUtil;

public class FindAccountService {

    public IdCertificationInfo getIdCertification(String email){
        if (getAccountNoByEmail(email).equals(""))
            return null;

        String certificationValue = getRandomValue();
        sendMail(email, certificationValue);

        return new IdCertificationInfo(certificationValue, email);
    }

    public PwCertificationInfo getPwCertification(String id, String email){
        int accountNo = getAccountNo(id, email);
        if (!isCorrectAccountNo(accountNo))
            return null;

        String certificationValue = getRandomValue();
        sendMail(email, certificationValue);

        return new PwCertificationInfo(accountNo, certificationValue);
    }
    public void sendMail(String email, String value){
        MailUtil mailUtil = new MailUtil();
        mailUtil.setTitle("[비하인드] 요청하신 인증번를 알려드립니다.\n");

        StringBuffer sb = new StringBuffer();
        sb.append("<h3 style='color:black'> 요청하신 인증번호를 알려드립니다.</h3>\n");
        sb.append("<h3 style='color:black'>아래의 인증번호를 인증번호 입력 창에 입력해 주세요.</h3>\n\n");
        sb.append("<h2 style='color:red'>" + value + "</h2>");
        sb.append("\n\n<h3 style='color:black'>감사합니다.</h3>");
        String messageText = sb.toString();

        mailUtil.setContentText(messageText);

        mailUtil.sendMail(email);
    }

    public String getRandomValue(){
        String value = Integer.toString((int)(Math.random() * 1000000));
        return value;
    }

    public String getAccountNoByEmail(String email) {
        AccountDao accountDao = new AccountDao();
        String accountNo = accountDao.getAccountNoByEmail(email);
        if (accountNo.equals(""))
            return "";
        return accountNo;
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
