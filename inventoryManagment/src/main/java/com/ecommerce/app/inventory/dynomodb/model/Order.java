package com.ecommerce.app.inventory.dynomodb.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.Date;

@DynamoDbBean
public class Order {

    private String orderId;
    private String customerId;

//    private Date orderDate;
    private String status;

    @DynamoDbPartitionKey
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    @DynamoDbSecondaryPartitionKey(indexNames = {"idx_order_customerId"})
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

  //  public Date getOrderDate() {
    //    return orderDate;
    //}

//    public void setOrderDate(Date orderDate) {
  //      this.orderDate = orderDate;
    //}

    @DynamoDbSortKey
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
      //          ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                '}';
    }
}
