package org.mytoypjt.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mytoypjt.config.WebMvcConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebMvcConfiguration.class)
@WebAppConfiguration
class LoginLogDaoTest {

    @Autowired
    LoginLogDao loginLogDao;

    @Test
    void writeLog() {

        boolean successed = true;
        try {
            // test content
            loginLogDao.writeLog(6, "작성된테스트로그2");
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void getLogsByAccountNo() {
        boolean successed = true;

        try {
            // test content
            loginLogDao.getLogsByAccountNo(4, 4).forEach(loginLog -> {
                System.out.println(loginLog.getAction());
            });

        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }
}