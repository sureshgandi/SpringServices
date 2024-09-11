package com.ecommerce.app.ecommerceservice.web;

import com.ecommerce.app.ecommerceservice.db.DatabaseContextHolder;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    private final JdbcTemplate jdbcTemplate;
    private final ResourceLoader resourceLoader;

    private final EntityManager manager;
    private final DataSource dataSource;
    private final ResourcePatternResolver resourcePatternResolver;
    private final CharacterEncodingFilter character;

    @Autowired
    public DatabaseController(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader, EntityManager manager, DataSource dataSource, ResourcePatternResolver resourcePatternResolver, CharacterEncodingFilter character) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
        this.manager = manager;
        this.dataSource = dataSource;
        this.resourcePatternResolver = resourcePatternResolver;
        this.character = character;
    }
    @RequestMapping(path = "/create/{id}", method = RequestMethod.GET)
    public String createDatabase2(@PathVariable String id) {
        try {
            jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS "+id);

            return "Database created successfully.";
        } catch (Exception e) {
            return "Failed to create database: " + e.getMessage();
        }
    }


    @RequestMapping(path = "/load-tables",method = RequestMethod.GET)
    public String loadTables() throws IOException {
        try {

            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:/sql/*.sql");
            for (Resource resource : resources) {
                System.out.println("Sized ===LL "+resources.length);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                    String sqlScript = reader.lines().collect(Collectors.joining("\n"));
                    System.out.println("SqlScript =====>>> "+sqlScript);
                    jdbcTemplate.execute(sqlScript);
                }
            }
            return "Tables loaded successfully.";
        } catch(IOException e){
            return "Failed to load tables: " + e.getMessage();
        }
    }

    @RequestMapping(path = "/load",method = RequestMethod.GET)
    public String loadTable() throws IOException {
        try {
           // ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("ecomm.sql"));
            ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("ecomm.sql"));
            //jdbcTemplate.execute(script);
            System.out.println("SQL script executed successfully!");
        } catch (SQLException e) {
            System.err.println("Error executing SQL script: " + e.getMessage());
        }

        return "Loaded tables successfully";
    }

    @GetMapping("/createDatabase/{databaseName}")
    public String createDatabase(@PathVariable String databaseName) {
        try {
            // Create database
            jdbcTemplate.execute("CREATE DATABASE " + databaseName);
            InputStream inputstream=new ClassPathResource("ecomm.sql").getInputStream();


            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream))) {
                String sqlScript = reader.lines().collect(Collectors.joining("\n"));
                System.out.println("SqlScript =====>>> "+sqlScript);
                jdbcTemplate.execute(sqlScript);
            }

            // Execute SQL script
 //           jdbcTemplate.execute(scriptBuilder.toString());

            return "Database created and SQL script loaded successfully: " + databaseName;
        } catch (Exception e) {
            return "Error creating database or loading SQL script: " + e.getMessage();
        }
    }

    @RequestMapping(path = "/load/{database}",method = RequestMethod.GET)
    public String load2Table(@PathVariable String database) throws IOException {
        try {

            Connection connection=dataSource.getConnection();
            Statement smt = connection.createStatement();
            smt.execute("CREATE DATABASE " + database);
          //  smt.execute("USE " + database);
            DatabaseContextHolder.setCurrentDatabase(database);
             //System.out.println("Url ==> "+sqlurl+" DataSource ->. "+smt.getConnection());
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("ecomm.sql"));
        } catch (SQLException e) {
            System.err.println("Error executing SQL script: " + e.getMessage());
        }

        return "Loaded tables successfully";
    }


    }