package com.Project.springboot.Dto;

import lombok.Getter;

@Getter
public class LoginRequest {

    // Getters and Setters
    private String email;
    private String password;

    // Default constructor is required for JSON Parsing
    public LoginRequest() {
    }

    // Constructor
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}