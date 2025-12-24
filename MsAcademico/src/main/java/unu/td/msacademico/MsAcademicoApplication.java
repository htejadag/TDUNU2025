package unu.td.msacademico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MsAcademicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAcademicoApplication.class, args);
	}

}
