package com.ecommerce.app.inventory.dynomodb.repository;

import com.ecommerce.app.inventory.config.Properties;
import com.ecommerce.app.inventory.dynomodb.model.Order;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.ProjectionType;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

@Service
public class OrderRepository {
    private final DynamoDbTable<Order> orderTable;

    private final DynamoDbClient dynamoDBClient;
    private final Properties properties;
    private final String tableName="OrderTable";


    public OrderRepository(DynamoDbEnhancedClient enhancedClient, DynamoDbClient dynamoDBClient, Properties properties) {
        this.orderTable = enhancedClient.table(tableName, TableSchema.fromBean(Order.class));
        this.dynamoDBClient = dynamoDBClient;
        this.properties = properties;
    }

    @PostConstruct
    public void init() {
        if (properties.isCreateTable()) {
            try {
                dynamoDBClient.deleteTable(
                        DeleteTableRequest.builder().tableName(tableName).build());
            } catch (ResourceNotFoundException rnfe) {
                System.out.println("Dynamo table not found");
            }

            orderTable.createTable(builder -> builder
                    .globalSecondaryIndices(builder3 -> builder3
                            .indexName("idx_order_customerId")

                            .projection(builder2 -> builder2
                                    .projectionType(ProjectionType.ALL))
                            .provisionedThroughput(builder4 -> builder4
                                    .writeCapacityUnits(1L)
                                    .readCapacityUnits(1L)))
                    .provisionedThroughput(b -> b
                            .readCapacityUnits(1L)
                            .writeCapacityUnits(1L)
                            .build()));

            dynamoDBClient.waiter().waitUntilTableExists(b -> b.tableName(tableName));
        }
    }

    public void saveOrder(Order order){
        orderTable.putItem(order);
    }

    public Order updateOrder(Order order){
        return orderTable.updateItem(order);
    }

    public Order getOrderById(String id){
        return orderTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public PageIterable<Order> fetchAllOrders(){

        return orderTable.scan();
    }

}
