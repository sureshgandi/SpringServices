package com.ecommerce.app.inventory.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "amazon.dynamodb")
public class Properties {
    private String endpoint;
    private String accesskey;
    private String secretkey;
    private String region;

    private boolean isCreateTable;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isCreateTable() {
        return isCreateTable;
    }

    public void setCreateTable(boolean createTable) {
        isCreateTable = createTable;
    }
}
