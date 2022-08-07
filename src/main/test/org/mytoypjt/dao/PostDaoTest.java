package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Profile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostDaoTest {

    public PostDao postDao;

    @BeforeEach
    public void init(){
        postDao = new PostDao();
    }

    @Test
    void getPosts() {
        boolean successed = true;

        try {
            List<Post> postList = postDao.getPosts(PostSortType.WEEKS_FAVORITE,1, 12);
            for (Post p : postList) {
                System.out.println(p.getPostNo() + ") : " + p.getPictureNo());
            }
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }



        assertEquals(true, successed);
    }

    @Test
    void modifyLike() {
        boolean successed = true;
        try {
            // test content
//            postDao.modifyLike(15,19,false);
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
            Profile profile = new Profile(100);
            profile.setNicname("nice name");
            // test content
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void getPostsByAccountNo() {
        boolean successed = true;
        try {
            // test content
            postDao
                    .getPostsByAccountNo(19)
                    .forEach((post) -> System.out.println(post.getContent()));
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void updatePost() {

        boolean successed = true;
        try {
            // test content
            this.postDao.updatePost(new Post());
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }
}