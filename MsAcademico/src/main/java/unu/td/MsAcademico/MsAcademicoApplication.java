package unu.td.MsAcademico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MsAcademicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAcademicoApplication.class, args);
	}

}
