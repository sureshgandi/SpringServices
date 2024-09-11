package com.ecommerce.app.inventory.domain;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

public class Provider {

    private String providerId;
    private String providerName;
    private String providerAddress;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "providerId='" + providerId + '\'' +
                ", providerName='" + providerName + '\'' +
                ", providerAddress='" + providerAddress + '\'' +
                '}';
    }
}
