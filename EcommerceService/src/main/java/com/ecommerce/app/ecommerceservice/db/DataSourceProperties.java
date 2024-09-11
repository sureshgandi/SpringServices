package com.ecommerce.app.ecommerceservice.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProperties {
    private List<DatabaseInfo> databases;

    public List<DatabaseInfo> getDatabases() {
        return databases;
    }

    public void setDatabases(List<DatabaseInfo> databases) {
        this.databases = databases;
    }
}
