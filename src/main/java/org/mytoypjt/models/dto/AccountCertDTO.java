package org.mytoypjt.models.dto;

import lombok.Getter;
import lombok.Setter;
import org.mytoypjt.models.entity.Account;

@Getter @Setter
public class AccountCertDTO {
    Account account;
    String certValue;

    public AccountCertDTO(Account account, String certValue) {
        this.account = account;
        this.certValue = certValue;
    }
}
