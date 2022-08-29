package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mytoypjt.config.WebMvcConfiguration;
import org.mytoypjt.models.entity.Account;
import org.mytoypjt.utils.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfiguration.class}, loader = AnnotationConfigContextLoader.class)
class AccountDaoTest {
    AccountDao accountDao;


    @BeforeEach
    void executeBefore(){
        System.out.println("It's before");
        accountDao = new AccountDao(DBUtil.getBasicDataSource());
    }

    @Test
    void getAccountNo(){
        boolean successed = true;
        try {
            // test content

            System.out.println(accountDao.getAccountNo("dkproh", "1234"));

        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);

    }

    @Test
    void getAccountListByEmail() {

        boolean successed = true;
        try {
            // test content
            this.accountDao.getAccountListByEmail("dkproh@gmail.com").forEach(System.out::println);
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void setAccountPw() {
        boolean successed = true;
        try {
            // test content
            this.accountDao.setAccountPw(19, "1234");
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void createAccount() {

        boolean successed = true;
        try {
            // test content
            this.accountDao.createAccount(null);
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}