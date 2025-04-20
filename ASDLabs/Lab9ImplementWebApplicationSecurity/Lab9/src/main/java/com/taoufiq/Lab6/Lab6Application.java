package com.taoufiq.Lab6;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.taoufiq.Lab6.Repositories.UserRepository;

@SpringBootApplication
public class Lab6Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab6Application.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository userRepository) {
		return args -> {
			System.out.println("Users in DB:");
			userRepository.findAll().forEach(user -> {
				System.out.println(user.getUsername() + " -> " + user.getRoles());
			});
		};
	}
}
