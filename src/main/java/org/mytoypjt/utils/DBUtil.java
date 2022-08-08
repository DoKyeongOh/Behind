package org.mytoypjt.utils;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import javax.sql.DataSource;
import java.sql.*;

public class DBUtil {

    private static String url = "jdbc:mysql://localhost:3306/behind?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static String id="behind_admin";
    private static String pw="1234";
    private static BasicDataSource dataSource = null;
    private static DBUtil dbUtil = null;

    private DBUtil(){
        createConnectionPool();
    }

    public static DBUtil getInstance(){
        if (dbUtil == null)
            dbUtil = new DBUtil();
        return dbUtil;
    }

    public Connection getConnection() {
        try {
//            return this.dbUtil.getConnectionByDataSource();
            return DriverManager.getConnection("jdbc:apache:commons:dbcp:cp");
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            return DriverManager.getConnection(url, id, pw);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    public Connection getConnectionByDataSource(){
        DataSource ds = getBasicDataSource();

        try {
            return ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static synchronized DataSource getBasicDataSource(){
        if (dataSource != null) {
            return dataSource;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataSource = new BasicDataSource();

        dataSource.setUsername(id);
        dataSource.setPassword(pw);
        dataSource.setUrl(url);

        dataSource.setMinIdle(10);
        dataSource.setMaxTotal(50);
        dataSource.setMaxOpenPreparedStatements(100);

        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1000L * 60L * 1L);
        return dataSource;
    }
}
