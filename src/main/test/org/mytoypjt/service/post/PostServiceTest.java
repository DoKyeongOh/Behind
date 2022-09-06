package org.mytoypjt.service.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.entity.Profile;

import org.junit.runner.RunWith;
import org.mytoypjt.config.WebMvcConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfiguration.class})
class PostServiceTest {

    @Autowired
    PostService postService;

    @BeforeEach
    public void init(){
        // init param
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

            this.postService.createPost(new Post());
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @org.junit.jupiter.api.Test
    void refreshCommentCountOfAllPost() {
        boolean successed = true;

        try {
            // test content
            postService.refreshCommentCountOfAllPost();
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }
}