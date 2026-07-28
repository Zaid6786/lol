package com.example.demo.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Profiles;

public class ChatbotTestInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext context) {
		ConfigurableEnvironment env = context.getEnvironment();
		if (env.acceptsProfiles(Profiles.of("chatbot-test"))) {
			String excludes = "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration," +
				"org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration," +
				"org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration," +
				"org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration," +
				"org.springframework.boot.data.jpa.autoconfigure.JpaRepositoriesAutoConfiguration," +
				"org.springframework.boot.autoconfigure.sql.init.DataSourceInitializationAutoConfiguration";

			System.setProperty("spring.autoconfigure.exclude", excludes);
			System.setProperty("spring.boot.autoconfigure.exclude", excludes);
			System.setProperty("spring.data.jpa.repositories.enabled", "false");
		}
	}
}
