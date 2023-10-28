package kr.co.ajoutee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TodoteeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoteeApplication.class, args);
	}

}
