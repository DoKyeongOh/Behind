package org.mytoypjt.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PwCertificationInfo {

    String certificationValue = "";
    int accountNo = -1;

    public PwCertificationInfo() {}
    public PwCertificationInfo(int accountNo, String certificationValue) {
        this.certificationValue = certificationValue;
        this.accountNo = accountNo;
    }
}
