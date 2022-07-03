package org.mytoypjt.models.dto;

public class IdCertificationInfo {

    String certificationValue = "";
    String emailAddress = "";

    public IdCertificationInfo() {}

    public IdCertificationInfo(String certificationValue, String emailAddress) {
        this.certificationValue = certificationValue;
        this.emailAddress = emailAddress;
    }

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

}
