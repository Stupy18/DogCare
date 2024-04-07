package com.Project.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
public class DogWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogWebAppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/users").allowedOrigins("http://localhost:8080").allowedMethods("POST", "GET");
				registry.addMapping("/users/{id}").allowedOrigins("http://localhost:8080").allowedMethods("POST", "DELETE");
				registry.addMapping("/auth/login").allowedOrigins("http://localhost:8080").allowedMethods("POST");
			}
		};

	}
}
