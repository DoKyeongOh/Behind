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
                return 0;
            }
        });


        // 로그부터 구현하고 해야할듯..?
        // 나의 활동이기 때문에 로그내역을 가져와서 구현해야함.

        return null;
    }
}
