package com.ecommerce.app.inventory.web;

import com.ecommerce.app.inventory.dynomodb.model.*;
import com.ecommerce.app.inventory.dynomodb.db.InventorySystem;
import com.ecommerce.app.inventory.dynomodb.repository.InventorySystemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.paginators.QueryIterable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventorySystemController {

    @Autowired
    private DynamoDbClient dynamoDbClient;

    @Autowired
    private DynamoDbEnhancedClient enhancedClient;


    @Autowired
    InventorySystemRepository inventoryRepository;
    @RequestMapping("/")
    public String inventoryWelcome(){
        return "Welcome to Inventory System";
    }

    @RequestMapping("/id/{id}")
    public ResponseEntity<InventorySystem> getinventoryById(@PathVariable String id){
        InventorySystem product = inventoryRepository.getById(id);
        return ResponseEntity.ok(product);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String createinventory(@RequestBody InventorySystem inventorySystem){
        inventoryRepository.saveInventory(inventorySystem);
        return "Successfully Created";
    }

/*    @RequestMapping("/search")
    public InventorySystem searchinventoryByName(@RequestParam String name){
        return inventoryRepository.searchByName(name);
    }
*/

    @GetMapping("/search2")
    public List<Page<InventorySystem>> searchCustomerByName(@RequestParam String name) {
        DynamoDbTable<InventorySystem> table = enhancedClient.table("InventorySystem", TableSchema.fromBean(InventorySystem.class));


        // Assuming Customer class has a field called 'name'
        QueryConditional q = QueryConditional.keyEqualTo(Key.builder().partitionValue(name).build());
        //       table.index("CustomerNameIndex").query(r -> r.queryConditional(c -> c.keyCondition(kc -> kc.partitionValue(name))))
        //             .items().forEach(System.out::println);

        return table.index("CustomerNameIndex").query(q).stream().toList();
    }

    @GetMapping("/searchCustomer")
    public void searchCustomerByName1(@RequestParam String name) {
        DynamoDbTable<InventorySystem> table = enhancedClient.table("InventorySystemTable", TableSchema.fromBean(InventorySystem.class));

        // Perform a query operation to find items with the desired customer name
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":name", AttributeValue.builder().s(name).build());

        QueryRequest queryRequest = QueryRequest.builder()
                .tableName("InventorySystemTable")
                .indexName("CustomerNameIndex")
                .keyConditionExpression("customerName = :name")
                .expressionAttributeValues(expressionValues)
                .build();

        QueryIterable queryResponse = dynamoDbClient.queryPaginator(queryRequest);

        List<InventorySystem> results = new ArrayList<>();

        for (Map<String, AttributeValue> item : queryResponse.items()) {
            String customerId = item.get("customerId").s(); // Assuming customerId is present in the InventorySystem table
            System.out.println("Item ===>> "+item+", CustomerId "+customerId);
            //Customer customer = customerTable.getItem(i -> i.key(k -> k.partitionValue(customerId)));
            //System.out.println("InventorySystem: " + item + ", Customer: " + customer);
        }


        // Process the results
        results.forEach(System.out::println);

    }

    @RequestMapping("/type/{name}")
    public InventorySystem searchinventoryByType(@PathVariable String name){
        return inventoryRepository.searchByType(name);
    }

    @RequestMapping("/product")
    public InventorySystem searchProductByCode(@RequestParam String code){
        return inventoryRepository.searchProductByCode(code);
    }
    @RequestMapping("/get")
    public InventorySystem getinventory(){
        InventorySystem inv = new InventorySystem();

        Customer cust=new Customer();
        cust.setCustomerAddress("HYD");
        cust.setCustomerId("2322");
        cust.setName("Arjun");

        inv.setCustomer(cust);

        Provider provider=new Provider();
        provider.setProviderAddress("Delhi");
        provider.setProviderName("LG Company");
        provider.setProviderId("12323");

        inv.setProvider(provider);

        Product product=new Product();
        product.setDescription("1.5 ton AC Electronics");
        product.setName("LG Product");
        product.setProductCode("LGPRO1");
        product.setPrice(35000);
        product.setCategoryId("34333344");
        product.setProductId("34543");

        Warehouse warehouse=new Warehouse();
        warehouse.setWarehouseId("232342");
        warehouse.setName("HYD Max steels");
        warehouse.setLocation("HYD");
        inv.setWarehouse(warehouse);

        Stock stock=new Stock();
        stock.setStockId("3443");
        stock.setQuantityOnHand(12);
        stock.setProductId(product.getProductId());
        stock.setWarehouseId(warehouse.getWarehouseId());
        inv.setStock(stock);

        Order order=new Order();
        order.setOrderId("343233");
        order.setCustomerId(cust.getCustomerId());
        order.setStatus("Initial");
        inv.setOrder(order);
        inv.setType("PRODUCT");
        inv.setProduct(product);
        inv.setWarehouse(warehouse);
        return inv;
    }

    @PostConstruct
    public void init(){

        // Define the request to create the index
        CreateGlobalSecondaryIndexAction indexAction = CreateGlobalSecondaryIndexAction.builder()
                .indexName("CustomerNameIndex")
                .keySchema(
                        KeySchemaElement.builder().attributeName("customerName").keyType(KeyType.HASH).build()
                )
                .projection(Projection.builder().projectionType(ProjectionType.ALL).build())
                .provisionedThroughput(ProvisionedThroughput.builder().readCapacityUnits(1L).writeCapacityUnits(1L).build())
                .build();

        UpdateTableRequest updateTableRequest = UpdateTableRequest.builder()
                .tableName("InventorySystemTable")
                .attributeDefinitions(
                        AttributeDefinition.builder().attributeName("customerName").attributeType(ScalarAttributeType.S).build()
                )
                .globalSecondaryIndexUpdates(GlobalSecondaryIndexUpdate.builder().create(indexAction).build())
                .build();

        // Execute the update request to create the index
        dynamoDbClient.updateTable(updateTableRequest);

        // Wait for the index creation to complete
        dynamoDbClient.waiter().waitUntilTableExists(b -> b.tableName("InventorySystem"));


    }
}
