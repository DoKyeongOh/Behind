package org.mytoypjt.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.models.dto.PostSortType;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.utils.DBUtil;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostDaoTest {

    public PostDao postDao;

    @BeforeEach
    public void init(){
        postDao = new PostDao(DBUtil.getBasicDataSource());
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
            // test content
            Post post = new Post(
                    "test title333",
                    "test content",
                    new Date(),
                    0,
                    0,
                    19,
                    1,
                    true,
                    true,
                    "admin",
                    "천안시");
            this.postDao.createPost(post);
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
            Post post = postDao.getPost(40);
            post.setContent(post.getContent() + ", 2회 수정됨");
            this.postDao.updatePost(post);
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void getTotalPostCount() {
        boolean successed = true;
        try {
            // test content
            System.out.println(this.postDao.getTotalPostCount());
        }catch (Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(true, successed);
    }

    @Test
    void getPostNoListOfAllPost() {
        boolean successed = true;

        try {
            // test content
            postDao.getPostNoListOfAllPost().forEach(System.out::println);
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }

    @Test
    void saveCommentCount() {
        boolean successed = true;

        try {
            // test content
            postDao.saveCommentCount(12, 7);
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }
}