package org.mytoypjt.dao;

import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Profile;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    ProfileDao profileDao = new ProfileDao();


    @Test
    void createProfile() {
        boolean a = true;
        try {
            profileDao.createProfile(new Profile(7));
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            a = false;
        }

        assertEquals(true,a);
    }

    @Test
    void updateProfile() {
        boolean test = true;
        try {
            java.util.Date date = new java.util.Date();
            Date sqlDate = new Date(date.getTime());
            profileDao.updateProfile(new Profile(10, "BehindAdmin", sqlDate, "choen-an", 26, "M", 1));
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            test = false;
        }

        assertEquals(true, test);
    }
}