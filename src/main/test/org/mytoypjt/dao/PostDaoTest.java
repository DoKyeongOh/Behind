package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.dto.PostSortType;

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

}