package org.mytoypjt.models.dto;

public class PwCertificationInfo {

    String certificationValue = "";
    int accountNo = -1;

    public PwCertificationInfo() {}
    public PwCertificationInfo(int accountNo, String certificationValue) {
        this.certificationValue = certificationValue;
        this.accountNo = accountNo;
    }
    public String getCertificationValue() {
        return certificationValue;
    }

    public void setCertificationValue(String certificationValue) {
        this.certificationValue = certificationValue;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }
}
