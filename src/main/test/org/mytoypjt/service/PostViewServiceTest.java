package org.mytoypjt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Post;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostViewServiceTest {
    public PostViewService postViewService;

    @BeforeEach
    public void init(){
        // init param
        postViewService = new PostViewService();
    }

    @Test
    void getPostListByPage() {
        boolean successed = true;
        try {
            List<Post> postList = postViewService.getDefaultPosts(1);
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}