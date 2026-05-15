package com.freeFire.tournment.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * DatabaseInitializer - Creates database if it doesn't exist
 * Runs before Hibernate/Spring context loads
 */
public class DatabaseInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        Environment env = context.getEnvironment();

        String datasourceUrl = env.getProperty("spring.datasource.url");
        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");

        // Skip if properties not configured
        if (datasourceUrl == null || username == null || password == null) {
            return;
        }

        try {
            // Extract database name from URL
            String databaseName = extractDatabaseName(datasourceUrl);

            // Connect to MySQL without specifying a database
            String rootUrl = datasourceUrl.substring(0, datasourceUrl.lastIndexOf("/"));

            createDatabaseIfNotExists(rootUrl, username, password, databaseName);

            System.out.println("✅ Database '" + databaseName + "' is ready for migrations");
        } catch (Exception e) {
            System.err.println("❌ Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Extract database name from JDBC URL
     * Example: jdbc:mysql://localhost:3306/freefire → freefire
     */
    private String extractDatabaseName(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    /**
     * Create database if not exists
     */
    private void createDatabaseIfNotExists(String rootUrl, String username, String password, String databaseName) 
            throws Exception {
        
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(rootUrl, username, password);
             Statement statement = connection.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            statement.execute(sql);

            System.out.println("Database creation checked: " + databaseName);
        }
    }
}
