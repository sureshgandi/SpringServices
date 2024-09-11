package com.ecommerce.app.inventory.dynomodb.repository;

import com.ecommerce.app.inventory.config.Properties;
import com.ecommerce.app.inventory.dynomodb.model.Warehouse;
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
public class WarehouseRepository {
    private final DynamoDbTable<Warehouse> warehouseTable;

    private final DynamoDbClient dynamoDBClient;
    private final Properties properties;
    private final String tableName="WarehouseTable";


    public WarehouseRepository(DynamoDbEnhancedClient enhancedClient, DynamoDbClient dynamoDBClient, Properties properties) {
        this.dynamoDBClient = dynamoDBClient;
        this.properties = properties;
        this.warehouseTable = enhancedClient.table(tableName, TableSchema.fromBean(Warehouse.class));
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

            warehouseTable.createTable(builder -> builder
                    .globalSecondaryIndices(builder3 -> builder3
                            .indexName("idx_warehouse_name")

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

    public void saveOrder(Warehouse product){
        warehouseTable.putItem(product);
    }

    public Warehouse updateWarehouse(Warehouse Warehouse){
        return warehouseTable.updateItem(Warehouse);
    }

    public Warehouse getWarehouseById(String id){
        return warehouseTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public PageIterable<Warehouse> fetchAllWarehouses(){

        return warehouseTable.scan();
    }


}
