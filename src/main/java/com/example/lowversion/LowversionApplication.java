package com.example.lowversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class LowversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(LowversionApplication.class, args);
	}

}
