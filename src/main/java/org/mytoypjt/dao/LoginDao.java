package org.mytoypjt.dao;

import org.mytoypjt.utils.DBUtil;

import java.sql.Connection;

public class loginDao {
    Connection conn = null;

    public loginDao() {
        conn = new DBUtil().getConnection();
    }

    public int getUserId(String id, String pw){
        return -1;
    }
}
