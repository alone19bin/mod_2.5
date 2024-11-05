package com.maxim.spring_security_rest_api_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SourceFilteringListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityRestApiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityRestApiAppApplication.class, args);
	}
}
