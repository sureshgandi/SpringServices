package com.ecommerce.app.inventory.dynomodb.repository;

import com.ecommerce.app.inventory.config.Properties;
import com.ecommerce.app.inventory.dynomodb.model.Category;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.ProjectionType;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

@Service
public class CategoryRepository {

    private final DynamoDbTable<Category> categoryTable;

    private final DynamoDbClient dynamoDBClient;
    private final Properties properties;
    private final String tableName="CategoryTable";

    public CategoryRepository(DynamoDbEnhancedClient enhancedClient, DynamoDbClient dynamoDBClient, Properties properties) {
        this.categoryTable = enhancedClient.table(tableName, TableSchema.fromBean(Category.class));
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

            categoryTable.createTable(builder -> builder
                    .globalSecondaryIndices(builder3 -> builder3
                            .indexName("idx_category_name")

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


    public void saveCategory(Category category)
    {

        categoryTable.putItem(category);
    }

    public void updateCategory(Category category){
        categoryTable.updateItem(category);
    }

    public Category getById(String id){
      //  categoryTable.getItem(Key.builder().partitionValue(id).build());

        return categoryTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public PageIterable<Category> searchNyName(String name){
        //  categoryTable.getItem(Key.builder().partitionValue(id).build());

        return categoryTable.query(QueryConditional.keyEqualTo(k -> k.partitionValue(name)));
    }

    public PageIterable<Category> fetchAlCustomers(){
        return categoryTable.scan();
    }

}
