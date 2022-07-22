package org.mytoypjt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.dao.PostDao;
import org.mytoypjt.models.entity.Profile;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    PostDao postDao;

    @BeforeEach
    public void init() {
        postDao = new PostDao();
    }


    @Test
    void isLikePost(){

        boolean successed = true;

        try {
            PostService postService = new PostService();
            System.out.println(postService.isLikePost("1",""));
        } catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }

        assertEquals(true, successed);

    }

}