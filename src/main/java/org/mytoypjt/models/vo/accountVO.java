package org.mytoypjt.models.vo;

import lombok.Getter;
import lombok.Setter;
import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.models.entity.Account;

@Getter @Setter
public class accountVO {
    private String id;
    private String password;
    private String passwordCheck;
    private String email;

    public accountVO(String id, String pw, String pwCheck, String email) {
        this.id = id;
        this.password = pw;
        this.passwordCheck = pwCheck;
        this.email = email;
    }

    public accountVO(Account account) {
        this.id = account.getId();
        this.password = account.getPassword();
        this.email = account.getEmail();
    }

}
