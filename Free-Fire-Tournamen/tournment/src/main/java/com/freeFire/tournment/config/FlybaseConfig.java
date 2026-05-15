package com.freeFire.tournment.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Configuration;

/**
 * Flyway Configuration for Database Migration
 * Provides static method to run migrations programmatically
 */
@Configuration
public class FlybaseConfig {

    /**
     * Run Flyway migrations programmatically
     * Called from TournmentApplication before context loads
     */
    public static void runMigrations(String datasourceUrl, String username, String password) {
        try {
            Flyway flyway = Flyway.configure()
                    .dataSource(datasourceUrl, username, password)
                    .locations("classpath:db/migration")
                    .baselineOnMigrate(true)
                    .load();
            
            flyway.migrate();
        } catch (Exception e) {
            System.err.println("❌ Flyway migration error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
