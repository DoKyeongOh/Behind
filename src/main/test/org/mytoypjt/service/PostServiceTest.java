package org.mytoypjt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.service.post.PostService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    PostService postService;

    @BeforeEach
    public void init(){
        // init param
//        postService = new PostService();
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

    @Test
    void createReply() {

        boolean successed = true;
        try {
            // test content
            this.postService.createReply("test content", null, 1, null);
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void updatePost(){

        boolean successed = true;
        try {
            // test content
            this.postService.updatePost(new Post());
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}