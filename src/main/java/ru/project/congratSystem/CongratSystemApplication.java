package ru.project.congratSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
//@EntityScan("ru.project.congratSystem.model")
public class CongratSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CongratSystemApplication.class, args);



	}

}
