package org.mytoypjt.models.dto.cert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.mytoypjt.exception.CustomException;
import org.mytoypjt.exception.ErrorCode;
import org.mytoypjt.models.dto.RegistrationRequestDTO;

@Data
@Builder
@AllArgsConstructor
public class RegistrationCertDTO {
    RegistrationRequestDTO registrationRequestDTO;
    String certValue;

    public void checkCertValue(String certValue) {
        if (certValue == null) {
            throw new CustomException(ErrorCode.CERT_INPUT_IS_NULL);
        }

        if (this.certValue == null) {
            throw new CustomException(ErrorCode.CERT_VALUE_IS_NULL);
        }

        if (!this.certValue.equals(certValue)) {
            throw new CustomException(ErrorCode.CERT_INPUT_IS_NOT_CORRECT);
        }
    }
}
