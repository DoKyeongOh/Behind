package org.mytoypjt.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mytoypjt.models.entity.Post;
import org.mytoypjt.utils.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
public class DBConfig {
    private static String url = "jdbc:mysql://localhost:3306/behind?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static String id="behind_admin";
    private static String pw="1234";

    @Bean
    public BasicDataSource dataSource() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        BasicDataSource dataSource = new BasicDataSource();

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

    @Bean
    public PlatformTransactionManager transactionManager(ApplicationContext ac) {
        return new DataSourceTransactionManager(ac.getBean(BasicDataSource.class));
    }

}
