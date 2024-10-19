package uz.pdp.WebAuto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebAutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAutoApplication.class, args);
	}

}
