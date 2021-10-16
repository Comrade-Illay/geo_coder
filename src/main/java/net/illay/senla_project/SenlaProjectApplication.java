package net.illay.senla_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SenlaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenlaProjectApplication.class, args);
	}

}
