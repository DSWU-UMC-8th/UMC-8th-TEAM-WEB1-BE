package com.example.UMC8th_MiniProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Umc8thMiniProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Umc8thMiniProjectApplication.class, args);
	}
}
