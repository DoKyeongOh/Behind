package org.mytoypjt.models.vo;

public class RegistVO {
    String id;
    String pw;
    String pwCheck;
    String email;
    String domain;

    public RegistVO() {
    }

    public RegistVO(String id, String pw, String pwCheck, String email, String domain) {
        this.id = id;
        this.pw = pw;
        this.pwCheck = pwCheck;
        this.email = email;
        this.domain = domain;
    }

    public String getEmailAddress(){
        return email + "@" + domain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getPwCheck() {
        return pwCheck;
    }

    public void setPwCheck(String pwCheck) {
        this.pwCheck = pwCheck;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
