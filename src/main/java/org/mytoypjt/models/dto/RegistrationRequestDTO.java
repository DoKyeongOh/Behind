package org.mytoypjt.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegistrationRequestDTO {
    String id;
    String pw;
    String pwCheck;
    String email;
    String domain;

    public RegistrationRequestDTO() {
    }

    public String getEmailAddress(){
        return email + "@" + domain;
    }

    public boolean isPasswordUsable() {
        if (pw == null || pwCheck == null) {
            return false;
        }
        return pw.equals(pwCheck);
    }
}
