package com.unu.ms.MsConsejo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MsConsejoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsConsejoApplication.class, args);
	}

}
