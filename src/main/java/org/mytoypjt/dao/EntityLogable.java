package org.mytoypjt.dao;

import org.mytoypjt.models.entity.AbstractEntityLog;

import java.util.List;

public interface EntityLogable {

    public void writeLog();
    public List<AbstractEntityLog> getLogsByAccountNo(int accountNo, int count);

}
