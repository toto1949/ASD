package com.taoufiq.elibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.taoufiq.demo", "com.controller"})
public class ElibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElibraryApplication.class, args);
	}

}
