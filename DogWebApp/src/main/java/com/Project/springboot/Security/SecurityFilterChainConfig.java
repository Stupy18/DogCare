package com.Project.springboot.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@CrossOrigin
public class SecurityFilterChainConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeRequests(authz -> authz
                        .requestMatchers("/users").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("**").permitAll()// Allow unauthenticated access to the "/users" endpoint
                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .httpBasic(withDefaults()); // Use HTTP Basic Authentication
        return http.build();
    }
}