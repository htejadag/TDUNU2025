package com.example.MsGeneral;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching
public class MsGeneralApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGeneralApplication.class, args);
	}

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
	}

}
