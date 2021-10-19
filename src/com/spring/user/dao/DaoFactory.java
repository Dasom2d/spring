package com.spring.user.dao;

import com.spring.user.uoUse.ConnectionMaker;
import com.spring.user.uoUse.DConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
//        UserDao userDao = new UserDao(connectionMaker());
        UserDao userDao = new UserDao(dataSource());
//        userDao.setConnectionMaker(connectionMaker());
        return userDao;
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/tobi?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("localmysql1234");
        return dataSource;
    }
}
