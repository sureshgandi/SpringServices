package com.ecommerce.app.inventory.dynomodb.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class Customer {

    private String customerId;
    private String name;
    private String customerAddress;

    @DynamoDbPartitionKey()
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

  //  @DynamoDbSecondaryPartitionKey(indexNames = {"idx_customer_customerName"})
//    public String getCustomerName() {
     //   return customerName;
    //}

    @DynamoDbSecondaryPartitionKey(indexNames = {"CustomerNameIndex"})
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public void setCustomerName(String customerName) {
      //  this.customerName = customerName;
   // }

    @DynamoDbSortKey
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                '}';
    }
}
