package org.mytoypjt.models.dto.cert;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.mytoypjt.exception.CustomException;
import org.mytoypjt.exception.ErrorCode;

@Data
@AllArgsConstructor
public class PwResetRequestDTO {
    String password;
    String passwordCheck;
    public void checkValue() {
        if (password == null || password.isEmpty()) {
            throw new CustomException(ErrorCode.INPUT_PW_IS_NULL);
        }

        if (passwordCheck == null || passwordCheck.isEmpty()) {
            throw new CustomException(ErrorCode.INPUT_PW_CHECK_IS_NULL);
        }
    }
}
