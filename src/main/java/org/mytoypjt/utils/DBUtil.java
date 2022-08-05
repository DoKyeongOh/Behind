package org.mytoypjt.utils;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.sql.*;

public class DBUtil {

    private String url = "jdbc:mysql://localhost:3306/behind?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String id="behind_admin";
    private String pw="1234";
    private static DBUtil dbUtil;

    private DBUtil(){
        createConnectionPool();
    }

    public static synchronized DBUtil getInstance(){
        if (dbUtil == null)
            dbUtil = new DBUtil();
        return dbUtil;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:apache:commons:dbcp:cp");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public synchronized Connection getConnectionInPool(){
        if (this.dbUtil == null)
            this.dbUtil = new DBUtil();

        return this.dbUtil.getConnection();
    }

    public void createConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, id, pw);
            PoolableConnectionFactory poolableConnectionFactory
                    = new PoolableConnectionFactory(connectionFactory, null);

            poolableConnectionFactory.setValidationQuery("Select 1");

            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setMinIdle(4);
            poolConfig.setMaxTotal(50);
            poolConfig.setTestWhileIdle(true);
            poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 1L);

            GenericObjectPool connectionPool = new GenericObjectPool(poolableConnectionFactory, poolConfig);
            poolableConnectionFactory.setPool(connectionPool);

            Class.forName("org.apache.commons.dbcp2.PoolingDriver");
            PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
            driver.registerPool("cp", connectionPool);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
