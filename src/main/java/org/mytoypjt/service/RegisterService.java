package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.utils.MailUtil;

public class RegisterService {
    public RegisterService() {
    }

    public boolean isUsableAccountId(String id){
        AccountDao accountDao = new AccountDao();
        boolean isExistId = accountDao.isExistId(id);
        return !isExistId;
    }

    public boolean sendAccountCert(String email){
        String certValue = getRandomValue().trim();
        sendMail(email, certValue);
        return true;
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
}
