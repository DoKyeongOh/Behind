package org.mytoypjt.service.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void createComment() {
        boolean successed = true;
        try {
            // test content
            Profile profile = new Profile(19);
            profile.setNicname("testCommentor");
            for (int i=0 ; i<30 ; i++) {
                postService.createComment("30", profile, "", "this is roof test" + Integer.toString(i));
            }
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}