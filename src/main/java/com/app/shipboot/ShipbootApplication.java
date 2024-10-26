package com.app.shipboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShipbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipbootApplication.class, args);
	}

}
