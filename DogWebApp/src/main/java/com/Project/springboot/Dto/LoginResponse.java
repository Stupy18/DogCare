package com.Project.springboot.Dto;

import lombok.Getter;

@Getter
public class LoginResponse {

    // Getters and Setters
    private String token;
    private String message;

    // Constructor
    public LoginResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}