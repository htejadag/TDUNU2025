package com.example.Comedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ComedorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComedorApplication.class, args);
	}

}
