package org.mytoypjt.dao;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDaoTest {

    AccountDao accountDao;

    @BeforeEach()
    public void init(){
        accountDao = new AccountDao();
    }

    @Test
    void isRegisteredEmail() {
        boolean test = true;
        try {
            test = accountDao.isRegisteredEmail("dkproh@gmail.com");
        } catch (Exception e) {
            e.printStackTrace();
            test = false;
        }
        assertEquals(true, test);
    }
}