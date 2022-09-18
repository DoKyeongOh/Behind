package org.mytoypjt.service.member;

import org.mytoypjt.dao.*;
import org.mytoypjt.models.entity.AbstractEntityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberService {

    @Autowired
    PostLogDao postLogDao;

    @Autowired
    CommentLogDao commentLogDao;

    @Autowired
    ReplyLogDao replyLogDao;

    @Autowired
    LikeLogDao likeLogDao;

    public MemberService() {
    }

    public List<AbstractEntityLog> getLogsByAccountNo(int accountNo, int count) {
        if (accountNo < 0)
            return null;

        List<AbstractEntityLog> logList = new ArrayList<>();

        logList.addAll(postLogDao.getLogsByAccountNo(accountNo, count));
        logList.addAll(commentLogDao.getLogsByAccountNo(accountNo, count));
        logList.addAll(replyLogDao.getLogsByAccountNo(accountNo, count));
        logList.addAll(likeLogDao.getLogsByAccountNo(accountNo, count));

        Collections.sort(logList, new Comparator<AbstractEntityLog>() {
            @Override
            public int compare(AbstractEntityLog o1, AbstractEntityLog o2) {
                return o2.getLoggingDate().compareTo(o1.getLoggingDate());
            }
        });

        return logList;
    }
}
