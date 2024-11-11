package com.maideit.maideit_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MaideitApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(MaideitApiApplication.class, args);
	}
}
