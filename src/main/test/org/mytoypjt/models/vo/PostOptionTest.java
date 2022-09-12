package org.mytoypjt.models.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostOptionTest {

    PostOption postOption;

    @BeforeEach
    public void init(){
        // init param
        postOption = new PostOption("1", "1");
        postOption.setPostLimitInMainPage();
    }

    @Test
    void setStartEndPageNo() {

        boolean successed = true;
        try {
            // test content
            postOption.setStartEndPageNo(10000);
            System.out.println("Start : " + postOption.getPageStartNo());
            System.out.println("End : " + postOption.getPageEndNo());
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}