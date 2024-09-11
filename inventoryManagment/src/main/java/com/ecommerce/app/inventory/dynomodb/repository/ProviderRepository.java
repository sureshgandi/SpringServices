package com.ecommerce.app.inventory.dynomodb.repository;


import com.ecommerce.app.inventory.config.Properties;
import com.ecommerce.app.inventory.dynomodb.model.Provider;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProviderRepository {

    private final DynamoDbTable<Provider> providerTable;

    private final DynamoDbClient dynamoDBClient;
    private final Properties properties;
    private final String tableName="ProviderTable";



    @Autowired
    public ProviderRepository(DynamoDbEnhancedClient enhancedClient, DynamoDbClient dynamoDBClient, Properties properties) {
        this.providerTable = enhancedClient.table(tableName, TableSchema.fromBean(Provider.class));
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

            providerTable.createTable(builder -> builder
                    .globalSecondaryIndices(builder3 -> builder3
                            .indexName("idx_provider_name")

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

    public void saveOrder(Provider product){
        providerTable.putItem(product);
    }

    public Provider updateProvider(Provider provider){
        return providerTable.updateItem(provider);
    }

    public Provider getProviderById(String id){
        return providerTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public PageIterable<Provider> fetchAllProviders(){

        return providerTable.scan();
    }

}
