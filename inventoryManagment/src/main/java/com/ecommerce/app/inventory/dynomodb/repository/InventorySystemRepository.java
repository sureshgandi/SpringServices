package com.ecommerce.app.inventory.dynomodb.repository;

import com.ecommerce.app.inventory.config.Properties;
import com.ecommerce.app.inventory.dynomodb.db.InventorySystem;
import com.ecommerce.app.inventory.dynomodb.model.Customer;
import com.ecommerce.app.inventory.dynomodb.model.Order;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class InventorySystemRepository {

    private final DynamoDbTable<InventorySystem> inventoryTable;
    private final DynamoDbClient dynamoDBClient;
    private final Properties properties;
    private final String tableName = "InventorySystemTable";

    public InventorySystemRepository(DynamoDbEnhancedClient enhancedClient, DynamoDbClient dynamoDBClient, Properties properties) {
        this.inventoryTable = enhancedClient.table(tableName, TableSchema.fromBean(InventorySystem.class));
        this.dynamoDBClient = dynamoDBClient;
        this.properties = properties;
    }

    @PostConstruct
    public void init() {
     //   if (properties.isCreateTable()) {
            try {
                dynamoDBClient.deleteTable(
                        DeleteTableRequest.builder().tableName(tableName).build());
            } catch (ResourceNotFoundException rnfe) {
                System.out.println("Dynamo table not found");
            }

            inventoryTable.createTable(builder -> builder
                    .globalSecondaryIndices(builder3 -> builder3
                            .indexName("idx_InventorySystem_type")
                          //  .indexName("customerNameIndex")
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

            // }
    }

    public void saveInventory(InventorySystem inventorySystem) {
        inventoryTable.putItem(inventorySystem);
    }

    public InventorySystem updateInventory(InventorySystem inventorySystem) {

        return inventoryTable.updateItem(inventorySystem);
    }

    public InventorySystem getById(String id) {

        return inventoryTable.getItem(Key.builder().partitionValue(id).build());
    }

    public InventorySystem searchByType(String name) {

        DynamoDbIndex<InventorySystem> index = inventoryTable.index("idx_InventorySystem_type");
        QueryConditional q = QueryConditional.keyEqualTo(Key.builder().partitionValue(name).build());
        Iterator<Page<InventorySystem>> result = index.query(q).iterator();
        List<InventorySystem> users = new ArrayList<>();

        while (result.hasNext()) {
            Page<InventorySystem> userPage = result.next();
            users.addAll(userPage.items());
        }
        return users.get(0);
    }


    public InventorySystem searchProductByCode(String productCode) {

     /*   QueryRequest queryRequest = QueryRequest.builder()
                .tableName(tableName)
                .indexName("idx_product_code") // Assuming you have an index on the 'address.city' attribute
                .keyConditionExpression("product.productCode = :productCode")
                .expressionAttributeValues(Map.of(":productCode", AttributeValue.builder().s(productCode).build()))
                .build();

        QueryResponse queryResponse = dynamoDBClient.query(queryRequest);
      System.out.println("Query ========>> "+queryResponse);
     */    DynamoDbIndex<InventorySystem> index = inventoryTable.index("");
        QueryConditional q = QueryConditional.keyEqualTo(Key.builder().partitionValue(productCode).build());
        Iterator<Page<InventorySystem>> result = index.query(q).iterator();
        List<InventorySystem> users = new ArrayList<>();

        while (result.hasNext()) {
            Page<InventorySystem> userPage = result.next();
            users.addAll(userPage.items());
        }
        return users.get(0);
      //  return new InventorySystem();
    }


    public List<InventorySystem> fetchAllCustomers() {
        List<InventorySystem> allRecords = new ArrayList<>();
        //ScanEnhancedRequest scanRequest = ScanEnhancedRequest.builder().build();

        // Paginate through results
        //PageIterable<Customer> scanResponse;
        // scanResponse = customerTable.scan(scanRequest);
        inventoryTable.scan().items().forEach(i -> allRecords.add(i));

        return allRecords;
        //return customerTable.scan();


    }
}
