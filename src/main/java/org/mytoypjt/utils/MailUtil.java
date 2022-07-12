package org.mytoypjt.utils;

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
    String senderName = "behindsender@gmail.com";
    String hostEmail = "behindsender@gmail.com";

    public MailUtil () {
        PropertiesUtil propertiesUtil = new PropertiesUtil("/mail_properties.properties");
        String password = (String) propertiesUtil.getProperty("gmail.password");
        setMailProperties();
        mailSession = Session.getDefaultInstance(mailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderName, password);
            }
        });
    }

    public void setMailProperties(){
        mailProperties = new Properties();
        mailProperties.put("mail.transport.protocol", "smtp");
        mailProperties.put("mail.smtp.host", hostServer);
        mailProperties.put("mail.smtp.port", connectPort);
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.ssl.enable", "true");
        mailProperties.put("mail.smtp.ssl.trust", "true");
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
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

    public void sendCertMail(String mailAddress, String value){
        setTitle("[비하인드] 요청하신 인증번를 알려드립니다.\n");

        StringBuffer sb = new StringBuffer();
        sb.append("<h3 style='color:black'> 요청하신 인증번호를 알려드립니다.</h3>\n");
        sb.append("<h3 style='color:black'>아래의 인증번호를 인증번호 입력 창에 입력해 주세요.</h3>\n\n");
        sb.append("<h2 style='color:red'>" + value + "</h2>");
        sb.append("\n\n<h3 style='color:black'>감사합니다.</h3>");
        String messageText = sb.toString();

        setContentText(messageText);

        sendMail(mailAddress);
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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

}

