package com.freeFire.tournment;

import com.freeFire.tournment.config.DatabaseInitializer;
import com.freeFire.tournment.config.FlybaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TournmentApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TournmentApplication.class);
		app.addInitializers(context -> {
			// Step 1: Create database if not exists
			new DatabaseInitializer().initialize(context);
			
			// Step 2: Run Flyway migrations
			String datasourceUrl = context.getEnvironment().getProperty("spring.datasource.url");
			String username = context.getEnvironment().getProperty("spring.datasource.username");
			String password = context.getEnvironment().getProperty("spring.datasource.password");
			
			if (datasourceUrl != null && username != null && password != null) {
				FlybaseConfig.runMigrations(datasourceUrl, username, password);
				System.out.println("✅ Flyway migrations completed successfully");
			}
		});
		app.run(args);
	}

}
