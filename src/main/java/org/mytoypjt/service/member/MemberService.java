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

    public MemberService() {
    }

    public List<AbstractEntityLog> loadEntityLogByAccountNo(int accountNo, int count) {
        if (accountNo < 0)
            return null;

        List<AbstractEntityLog> logList = new ArrayList<>();

        logList.addAll(postLogDao.getLogsByAccountNo(accountNo, count));
        logList.addAll(commentLogDao.getLogsByAccountNo(accountNo, count));
        logList.addAll(replyLogDao.getLogsByAccountNo(accountNo, count));

        Collections.sort(logList, new Comparator<AbstractEntityLog>() {
            @Override
            public int compare(AbstractEntityLog o1, AbstractEntityLog o2) {
                return o1.getLoggingDate().compareTo(o2.getLoggingDate());
            }
        });

        // 이젠 좋아요 로그만 남기면됨.

        return logList;
    }
}
