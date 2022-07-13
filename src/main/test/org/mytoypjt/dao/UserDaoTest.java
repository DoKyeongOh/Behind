package org.mytoypjt.dao;

import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.User;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao userDao = new UserDao();


    @Test
    void createUser() {
        boolean a = true;
        try {
            userDao.createUser(new User(7));
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            a = false;
        }

        assertEquals(true,a);
    }

    @Test
    void updateUser() {
        boolean test = true;
        try {
            java.util.Date date = new java.util.Date();
            Date sqlDate = new Date(date.getTime());
            userDao.updateUser(new User(10, "BehindAdmin", sqlDate, "choen-an", 26, "M", 1));
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            test = false;
        }

        assertEquals(true, test);
    }
}