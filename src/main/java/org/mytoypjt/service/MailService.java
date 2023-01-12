package org.mytoypjt.service;

import lombok.extern.slf4j.Slf4j;
import org.mytoypjt.exception.CustomException;
import org.mytoypjt.exception.ErrorCode;
import org.mytoypjt.utils.PropertiesUtil;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class MailService {
    String hostEmail = "behindsender@gmail.com";

    public MailService () {

    }

    private Session getMailSession() {
        PropertiesUtil propertiesUtil = new PropertiesUtil("/mail_properties.properties");
        String username = (String) propertiesUtil.getProperty("mail.gmail.username");
        String password = (String) propertiesUtil.getProperty("mail.gmail.password");
        String domain = (String) propertiesUtil.getProperty("mail.domain");

        Properties mailProperties = new Properties();
        mailProperties.put("mail.transport.protocol", "smtp");
        mailProperties.put("mail.smtp.host", domain);
        mailProperties.put("mail.smtp.port", "465");
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.ssl.enable", "true");
        mailProperties.put("mail.smtp.ssl.trust", "true");
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        return Session.getDefaultInstance(mailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private void sendMail(String receiver, String title, String content) {
        MimeMessage message = new MimeMessage(getMailSession());
        try {
            Address address = new InternetAddress(hostEmail);
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(title);

            Multipart mp = new MimeMultipart();
            MimeBodyPart mbp = new MimeBodyPart();

            mbp.setText(content, "UTF-8", "html");
            mp.addBodyPart(mbp);
            message.setContent(mp);

            Transport.send(message);

        } catch (Exception e) {
            throw new CustomException(ErrorCode.MAIL_SEND_FAILURE, e.getMessage());
        }
    }

    public void sendCertMail(String mailAddress, String value){
        String title = ("[비하인드] 요청하신 인증번를 알려드립니다.\n");

        StringBuffer sb = new StringBuffer();
        sb.append("<h3 style='color:black'> 요청하신 인증번호를 알려드립니다.</h3>\n");
        sb.append("<h3 style='color:black'>아래의 인증번호를 인증번호 입력 창에 입력해 주세요.</h3>\n\n");
        sb.append("<h2 style='color:red'>" + value + "</h2>");
        sb.append("\n\n<h3 style='color:black'>감사합니다.</h3>");
        String content = sb.toString();

        sendMail(mailAddress, title, content);
    }
}
