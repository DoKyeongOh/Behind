package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Post;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostDaoTest {

    public PostDao postDao;

    @BeforeEach
    public void init(){
        postDao = new PostDao();
    }


    @Test
    void getPostListByPage() {
        boolean successed = true;

        try {
            List<Post> postList = postDao.getPosts(1);
            for (Post p : postList) {
                System.out.println(p.getPostedDate());
            }
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }



        assertEquals(true, successed);


    }
}