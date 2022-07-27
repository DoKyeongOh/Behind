package org.mytoypjt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Post;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    PostService postService;

    @BeforeEach
    public void init(){
        // init param
        postService = new PostService();
    }

    @Test
    void getPageCount() {

        boolean successed = true;
        try {
            // test content
            System.out.println(postService.getRealTimePageCount());
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void getDaysPageCount() {
        boolean successed = true;
        try {
            // test content
            System.out.println(this.postService.getDaysPageCount());
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void getWeeksPageCount() {
        boolean successed = true;
        try {
            // test content
            System.out.println(this.postService.getWeeksPageCount());

        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void getPosterProfiles() {
        boolean successed = true;
        try {
            // test content
            List<Post> posts = new ArrayList<Post>();
            for (int i=19 ; i<=25 ; i++) {
                Post post = new Post();
                post.setAccountNo(i);
                posts.add(post);
            }

        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}