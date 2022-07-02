package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.utils.MailUtil;

public class FindAccountService {

    String certificationValue = "";

    public boolean sendMailForFindId(String email){
        if (getUserIdFromEmail(email).equals(""))
            return false;

        MailUtil mailUtil = new MailUtil();
        mailUtil.setTitle("[비하인드] 요청하신 인증번를 알려드립니다.\n");

        StringBuffer sb = new StringBuffer();
        sb.append("<h3 style='color:black'> 요청하신 인증번호를 알려드립니다.</h3>\n");
        sb.append("<h3 style='color:black'>아래의 인증번호를 인증번호 입력 창에 입력해 주세요.</h3>\n\n");
        sb.append("<h2 style='color:red'>" + getRandomValue() + "</h2>");
        sb.append("\n\n<h3 style='color:black'>감사합니다.</h3>");
        String messageText = sb.toString();

        mailUtil.setContentText(messageText);

        mailUtil.sendMail(email);
        return true;
    }

    public String getRandomValue(){
        String value = Integer.toString((int)(Math.random() * 1000000));
        setCertificationValue(value);
        return value;
    }

    public void setCertificationValue(String certificationValue) {
        this.certificationValue = certificationValue;
    }

    public String getCertificationValue() {
        return certificationValue;
    }

    public String getUserIdFromEmail(String email) {
        AccountDao accountDao = new AccountDao();
        String userId = accountDao.getUserIdFromEmail(email);
        if (userId.equals(""))
            return "";
        return userId;
    }
}
