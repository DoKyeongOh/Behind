package org.mytoypjt.utils;

import com.sun.mail.smtp.SMTPSSLTransport;
import com.sun.mail.smtp.SMTPTransport;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailUtil {
    Properties mailProperties;
    Session mailSession;
    String title = "Title is empty";
    String contentText = "Content is empty";
    String hostServer = "smtp.gmail.com";
    String connectPort = "465";
    String sendUserName = "behindsender@gmail.com";
    String sendUserPassword = "**********";
    String hostEmail = "behindsender@gmail.com";

    public MailUtil () {
        // 이메일 프로토콜인 smtp를 이용하여 메일송신
        // 보안 방식을 ssl로 사용.
        mailProperties = new Properties();
        mailProperties.put("mail.transport.protocol", "smtp");
        mailProperties.put("mail.smtp.host", hostServer);
        mailProperties.put("mail.smtp.port", connectPort);
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.ssl.enable", "true");
        mailProperties.put("mail.smtp.ssl.trust", "true");

        /*
        mail.smtp.socketFactory.class 프로퍼티를 지정하면
        지정된 factory impl Class가 smtp 소켓을 만들어준다.

        smtp 프로토콜은 소켓 방식 프로토콜이다.
        따라서 smtp 서버(구글, 네이버 등)에 소켓 방식으로 연결 후
        메일을 송신하는 등 행위를 하게된다.
        * */
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        mailSession = Session.getDefaultInstance(mailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendUserName, sendUserPassword);
            }
        });
    }

    public void sendMail(String receiver){
        MimeMessage message = new MimeMessage(this.mailSession);

        try {
            Address address = new InternetAddress(hostEmail);
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(this.title);

            Multipart mp = new MimeMultipart();
            MimeBodyPart mbp = new MimeBodyPart();

            mbp.setText(this.contentText, "UTF-8", "html");
            mp.addBodyPart(mbp);
            message.setContent(mp);

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Properties getMailProperties() {
        return mailProperties;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public String getSendUserPassword() {
        return sendUserPassword;
    }

    public void setSendUserPassword(String sendUserPassword) {
        this.sendUserPassword = sendUserPassword;
    }
}

