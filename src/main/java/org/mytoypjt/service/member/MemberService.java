package org.mytoypjt.service.member;

import org.mytoypjt.dao.*;
import org.mytoypjt.models.entity.AbstractEntityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    PostLogDao postLogDao;

    public MemberService() {
    }

    public List<AbstractEntityLog> loadEntityLogByAccountNo(int accountNo, int count) {
        if (accountNo < 0)
            return null;

        List<AbstractEntityLog> logList = new ArrayList<>();

        postLogDao.getLogsByAccountNo(accountNo, count);


        // 로그부터 구현하고 해야할듯..?
        // 나의 활동이기 때문에 로그내역을 가져와서 구현해야함.

        return null;
    }
}
