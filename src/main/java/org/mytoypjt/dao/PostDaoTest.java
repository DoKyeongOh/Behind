package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Post;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostDaoTest {

    PostDao postDao;

    @BeforeEach
    public void init(){
        // init param
        postDao = new PostDao();
    }

    @Test
    void getRealTimePosts() {
        boolean successed = true;

        try {
            // test content
            printPostList(postDao.getRealTimePosts(1,12));
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }

    @Test
    void getDaysFavoritePosts() {
        boolean successed = true;

        try {
            // test content
            printPostList(postDao.getDaysFavoritePosts(1,12));
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }

    @Test
    void getWeeksFavoritePosts() {
        boolean successed = true;

        try {
            // test content
            printPostList(postDao.getWeeksFavoritePosts(1,12));
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }

    void printPostList(List<Post> posts){
        posts.forEach(System.out::println);
    }
}