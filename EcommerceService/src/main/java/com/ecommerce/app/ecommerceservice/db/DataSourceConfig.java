package com.ecommerce.app.ecommerceservice.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();

        // Configure your data sources
        for (DatabaseInfo databaseInfo : dataSourceProperties.getDatabases()) {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(databaseInfo.getDriverClassName());
            dataSource.setUrl(databaseInfo.getUrl());
            dataSource.setUsername(databaseInfo.getUsername());
            dataSource.setPassword(databaseInfo.getPassword());
            dataSourceMap.put(databaseInfo.getName(), dataSource);
        }

        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMap.get("default")); // Set default data source
        return dynamicDataSource;
    }
}
