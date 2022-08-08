package org.mytoypjt.service.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    PostService postService;

    @BeforeEach
    public void init(){
        // init param
        postService = new PostService();
    }

    @Test
    void updatePost() {

        boolean successed = true;
        try {
            // test content
            postService.updatePost(new Post());
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void createPost() {
        boolean successed = true;
        try {
            // test content
            Profile profile = new Profile(19);
            profile.setNicname("테스트");
            profile.setCity("도시");

            this.postService.createPost(new Profile(19), new Post());
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}