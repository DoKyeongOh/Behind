package org.mytoypjt.models.dto.cert;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IdCertRequestDTO {
    String email;
    String domain;
    public String getEmailAddress() {
        return email + "@" + domain;
    }
}
