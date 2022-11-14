package org.mytoypjt.service.member;

import org.mytoypjt.dao.log.CommentLogDao;
import org.mytoypjt.dao.log.LikeLogDao;
import org.mytoypjt.dao.log.PostLogDao;
import org.mytoypjt.dao.log.ReplyLogDao;
import org.mytoypjt.models.etc.AbstractEntityLog;
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

        Collections.sort(logList, (o1, o2) -> o2.getLoggingDate().compareTo(o1.getLoggingDate()));

        return logList;
    }

    public List<String> getLoggingPathList(List<AbstractEntityLog> logList) {
        List<String> loggingPathList = new ArrayList<>();
        logList.forEach(log -> {
            String loggingPath = "";
            switch (log.getLogTypeIdentifier()) {
                case post: {
                    loggingPath = "/post?no=" + log.getEntityNo();
                    break;
                }

                case comment: {
                    loggingPath = "/comment?no=" + log.getEntityNo();
                    break;
                }

                case reply: {
                    loggingPath = "/reply?no=" + log.getEntityNo();
                    break;
                }

                case like: {
                    loggingPath = "/post?no=" + log.getEntityNo();
                    break;
                }
            }
            if (log.getLogMsg().equals("삭제"))
                loggingPath = "";
            if (log.getEntityNo() <= 0)
                loggingPath = "";
            loggingPathList.add(loggingPath);
        });
        return loggingPathList;
    }
}
