package com.ecommerce.app.inventory;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

@SpringBootApplication
public class InventoryManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagmentApplication.class, args);
    }

}
