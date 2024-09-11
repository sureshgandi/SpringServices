package com.ecommerce.app.ecommerceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EcommerceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceServiceApplication.class, args);
	}

}
