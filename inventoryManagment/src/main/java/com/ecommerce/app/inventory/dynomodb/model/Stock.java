package com.ecommerce.app.inventory.dynomodb.model;

import com.ecommerce.app.inventory.domain.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@DynamoDbBean
public class Stock {

    private String stockId;

    private String productId;
    private Warehouse warehouse;

    private String warehouseId;
    private int quantityOnHand;

    @DynamoDbPartitionKey
    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = {"idx_stock_productId"})
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = {"idx_stock_warehouseId"})
    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }



}
