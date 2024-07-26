package com.nagarro.customerauthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class CustomerAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAuthorizationApplication.class, args);
	}
}
