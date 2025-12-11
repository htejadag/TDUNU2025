package tdunu.MsPosgrado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class MsPosgradoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPosgradoApplication.class, args);
	}

}
