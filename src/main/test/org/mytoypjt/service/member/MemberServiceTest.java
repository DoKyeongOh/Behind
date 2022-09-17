package org.mytoypjt.service.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mytoypjt.dao.CommentLogDao;
import org.mytoypjt.dao.LikeLogDao;
import org.mytoypjt.dao.PostLogDao;
import org.mytoypjt.dao.ReplyLogDao;
import org.mytoypjt.utils.DBUtil;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void init(){
        // init param
        memberService = new MemberService();
        memberService.postLogDao = new PostLogDao(DBUtil.getBasicDataSource());
        memberService.commentLogDao = new CommentLogDao(DBUtil.getBasicDataSource());
        memberService.replyLogDao = new ReplyLogDao(DBUtil.getBasicDataSource());
        memberService.likeLogDao = new LikeLogDao(DBUtil.getBasicDataSource());

    }

    @Test
    void loadEntityLogByAccountNo() {
        boolean successed = true;

        try {
            // test content
            memberService.getLogsByAccountNo(6, 100);
        } catch(Exception e) {
            e.printStackTrace();
            successed = false;
        }
        assertEquals(successed, true);
    }
}