package org.mytoypjt.service;

import org.mytoypjt.dao.CommentDao;
import org.mytoypjt.dao.PostDao;
import org.mytoypjt.dao.ProfileDao;
import org.mytoypjt.dao.ReplyDao;
import org.mytoypjt.models.entity.Profile;
import org.mytoypjt.models.vo.ActivityVO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MemberService {

    private Connection connection;

    private PostDao postDao;
    private ProfileDao profileDao;
    private CommentDao commentDao;
    private ReplyDao replyDao;

    public MemberService() {
    }

    public ActivityVO getActivities(Profile profile) {
        if (!Profile.isCorrectProfile(profile))
            return null;

        ActivityVO activityVO = new ActivityVO(profile);
        List<Object> things = new ArrayList<Object>();

        postDao.getPostsByAccountNo(profile.getAccountNo()).forEach(things::add);
        commentDao.getCommentsByAccountNo(profile.getAccountNo()).forEach(things::add);
        replyDao.getRepliesByAccountNo(profile.getAccountNo()).forEach(things::add);
//        postDao.getLikesByAccountNo(profile.getAccountNo()).forEach(things::add);

        // 로그부터 구현하고 해야할듯..?
        // 나의 활동이기 때문에 로그내역을 가져와서 구현해야함.

        return null;
    }
}
