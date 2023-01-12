package org.mytoypjt.models.entity;

import lombok.Getter;
import org.mytoypjt.models.dto.RegistrationRequestDTO;

@Getter
public class Account {
    int accountNo;
    String id;
    String password;
    String email;

    public Account(int profileNo, String id, String password, String email) {
        this.accountNo = profileNo;
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public Account(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public Account(RegistrationRequestDTO dto) {
        id = dto.getId();
        password = dto.getPw();
        email = dto.getEmailAddress();
    }


}
