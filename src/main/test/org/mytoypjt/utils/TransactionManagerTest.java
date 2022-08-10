package org.mytoypjt.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Post;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class TransactionManagerTest {

    TransactionManager transactionManager;

    @BeforeEach
    public void init(){
        // init param
//        transactionManager = new TransactionManager();
    }

    @Test
    void entryService() {


    }

    @Test
    void getInstance() {

        boolean successed = true;
        try {
            // test content
            Post post = (Post) TransactionManager.getInstance(Post.class);
            post.getTitle();
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}