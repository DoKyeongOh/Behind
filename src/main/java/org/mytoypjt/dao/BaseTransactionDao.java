package org.mytoypjt.dao;

import java.sql.Connection;

public class BaseTransactionDao {

    Connection connection;

    public BaseTransactionDao() {
    }

    public BaseTransactionDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
