package com.service.MsTramiteTesis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MsTramiteTesisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTramiteTesisApplication.class, args);
	}

}
