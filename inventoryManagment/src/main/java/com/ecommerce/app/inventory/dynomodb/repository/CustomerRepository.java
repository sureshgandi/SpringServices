package com.ecommerce.app.inventory.dynomodb.repository;

import com.ecommerce.app.inventory.config.Properties;
import com.ecommerce.app.inventory.dynomodb.model.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.ProjectionType;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CustomerRepository {

    private final DynamoDbTable<Customer> customerTable;
    private final DynamoDbClient dynamoDBClient;
    private final Properties properties;
    private final String tableName="CustomerTable";
    public CustomerRepository(DynamoDbEnhancedClient enhancedClient, DynamoDbClient dynamoDBClient, Properties properties) {
        this.customerTable = enhancedClient.table(tableName, TableSchema.fromBean(Customer.class));
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

            customerTable.createTable(builder -> builder
                    .globalSecondaryIndices(builder3 -> builder3
                            .indexName("idx_customer_customerName")

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

    public void saveCustomer(Customer customer){
      //  System.out.println("Customer id --> "+customer.getCustomerId());
  ///      System.out.println("Customer Name --> "+customer.getCustomerName());
     //   System.out.println("Customer Address --> "+customer.getCustomerAddress());
        customerTable.putItem(customer);
    }

    public Customer updateCustomer(Customer customer){

        return customerTable.updateItem(customer);
    }

    public Customer getById(String id){
        //  categoryTable.getItem(Key.builder().partitionValue(id).build());

        DynamoDbIndex<Customer> index = customerTable.index("idx_customer_customerName");
        QueryConditional q = QueryConditional.keyEqualTo(Key.builder().partitionValue(id).build());
        Iterator<Page<Customer>> result = index.query(q).iterator();
        List<Customer> users = new ArrayList<>();

        while (result.hasNext()) {
            Page<Customer> userPage = result.next();
            users.addAll(userPage.items());
        }
//customerTable.getItem(r -> r.key(k -> k.partitionValue(id)));
        return users.get(0);
    }

    public Customer searchNyName(String name){

        //return customerTable.query(QueryConditional.keyEqualTo(k -> k.partitionValue(name)));
//        QueryConditional queryCondition = QueryConditional.keyEqualTo(k -> k.partitionValue(name));
  //      return customerTable.query(queryCondition);

        DynamoDbIndex<Customer> index = customerTable.index("idx_customer_customerName");
        QueryConditional q = QueryConditional.keyEqualTo(Key.builder().partitionValue(name).build());
        Iterator<Page<Customer>> result = index.query(q).iterator();
        List<Customer> users = new ArrayList<>();

        while (result.hasNext()) {
            Page<Customer> userPage = result.next();
            users.addAll(userPage.items());
        }
//customerTable.getItem(r -> r.key(k -> k.partitionValue(id)));
        return users.get(0);

    }

    public List<Customer> fetchAllCustomers(){
        List<Customer> allRecords = new ArrayList<>();
        //ScanEnhancedRequest scanRequest = ScanEnhancedRequest.builder().build();

        // Paginate through results
        //PageIterable<Customer> scanResponse;
           // scanResponse = customerTable.scan(scanRequest);
            customerTable.scan().items().forEach(i -> allRecords.add(i));

        return allRecords;
        //return customerTable.scan();
    }

}
