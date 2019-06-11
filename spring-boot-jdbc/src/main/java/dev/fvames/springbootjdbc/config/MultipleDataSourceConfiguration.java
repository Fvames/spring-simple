package dev.fvames.springbootjdbc.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


/**
 * 多数据源 DataSource 配置
 * @version 2019/6/10 14:38
 */
@Configuration
public class MultipleDataSourceConfiguration {

    @Bean
    @Primary
    public DataSource masterDataSource() {
        // com.mysql.cj.jdbc.Driver
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        DataSource dataSource = dataSourceBuilder
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://192.168.17.129:3306/test")
                .username("root")
                .password("123456")
                .build();
        return dataSource;
    }

    @Bean
    public DataSource salveDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        DataSource dataSource = dataSourceBuilder
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://192.168.17.129:3306/test2")
                .username("root")
                .password("123456")
                .build();

        return dataSource;
    }
}
