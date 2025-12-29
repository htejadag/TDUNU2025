package com.example.MsEvaluacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MsEvaluacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEvaluacionApplication.class, args);
	}

}
