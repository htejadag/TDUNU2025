package tdunu.MsCatalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MsCatalogoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCatalogoApplication.class, args);
    }
}
