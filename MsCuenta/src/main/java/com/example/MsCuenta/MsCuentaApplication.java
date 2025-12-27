package com.example.MsCuenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MsCuentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCuentaApplication.class, args);
	}

}
