package com.kms.baseSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BaseSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseSpringBootApplication.class, args);
	}

}
