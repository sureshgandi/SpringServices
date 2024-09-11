package com.ecommerce.app.ecommerceservice.web;

import com.ecommerce.app.ecommerceservice.service.DatabaseMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
public class DBController {

    private final DatabaseMigrationService migrationService;

    public DBController(DatabaseMigrationService migrationService) {
        this.migrationService = migrationService;
    }


    @GetMapping("/migrate")
    public String migrateDatabase() {
        //migrationService.migrateDatabase();
        return "Database migration completed.";
    }
}
