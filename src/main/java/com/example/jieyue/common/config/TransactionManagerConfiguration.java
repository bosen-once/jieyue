package com.example.jieyue.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Bosen
 * @date 2021/8/9 22:42
 */
@EnableTransactionManagement
@Configuration
public class TransactionManagerConfiguration {
    @Autowired
    private DataSource dataSource;

    public TransactionManager createTransactionManager(){
        return new DataSourceTransactionManager(dataSource);
    }
}
