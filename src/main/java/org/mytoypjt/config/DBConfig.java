package org.mytoypjt.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBConfig {
    private static String url = "jdbc:mysql://localhost:3306/behind?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static String id="behind_admin";
    private static String pw="1234";

    @Bean
    public BasicDataSource dataSource(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

}
