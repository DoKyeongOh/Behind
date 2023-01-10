package org.mytoypjt.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.mytoypjt.exception.CustomException;
import org.mytoypjt.exception.ErrorCode;

@Data
@AllArgsConstructor
public class PwCertDTO {

    String certValue;
    int accountNo;

    public boolean isUsable() {
        if (certValue == null || certValue.isEmpty()) {
            return false;
        }
        return true;
    }

    public void checkCertValue(String certValue) {
        if (getCertValue() == null || getCertValue().isEmpty()) {
            throw new CustomException(ErrorCode.CERT_VALUE_IS_NULL);
        }

        if (certValue == null || certValue.isEmpty()) {
            throw new CustomException(ErrorCode.CERT_INPUT_IS_NULL);
        }

        if (!getCertValue().equals(certValue)) {
            throw new CustomException(ErrorCode.CERT_FAILURE);
        }
    }
}
