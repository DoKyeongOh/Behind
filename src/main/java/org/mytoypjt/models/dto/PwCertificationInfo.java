package org.mytoypjt.models.dto;

public class PwCertificationInfo {

    String certificationValue = "";
    String emailAddress = "";
    String id = "";

    public PwCertificationInfo() {}

    public String getCertificationValue() {
        return certificationValue;
    }

    public void setCertificationValue(String certificationValue) {
        this.certificationValue = certificationValue;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
