package com.ecommerce.app.inventory.dynomodb.db;

import com.ecommerce.app.inventory.dynomodb.model.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@DynamoDbBean
public class InventorySystem {

    private String id;
    private String type;
    private Customer customer;
    private Provider provider;
    private Order order;
    private Stock stock;
    private Warehouse warehouse;

    private Product product;


    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = {"idx_InventorySystem_type"})
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

  //  @DynamoDbSecondaryPartitionKey(indexNames = {"customerNameIndex"})
    public Customer getCustomer() {
        return customer; }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

  //  @DynamoDbSecondaryPartitionKey(indexNames = "id_inventorySystem_code")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
