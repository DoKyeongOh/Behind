package org.mytoypjt.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class TransactionManagerTest {

    TransactionManager transactionManager;

    @BeforeEach
    public void init(){
        // init param
        transactionManager = new TransactionManager();
    }

    @Test
    void entryService() {
        try {
            Method method = transactionManager.getClass().getDeclaredMethod("entryService");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}