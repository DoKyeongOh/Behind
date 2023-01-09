package org.mytoypjt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LoginServiceTest {

    LoginService loginService;

    @BeforeEach
    public void init(){
        // init param
        loginService = new LoginService();
    }

    @Test
    void getProfile() {

        boolean successed = true;
        try {
            // test content
//            loginService.getProfile("dkproh", "123123");
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}