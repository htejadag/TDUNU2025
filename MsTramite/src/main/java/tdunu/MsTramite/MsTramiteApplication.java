package tdunu.MsTramite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class MsTramiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTramiteApplication.class, args);
	}

}
