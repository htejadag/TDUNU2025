package com.MsSimulacro.MsSimulacro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MsSimulacroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSimulacroApplication.class, args);
	}

}
