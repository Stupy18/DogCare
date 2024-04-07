package com.Project.springboot.Dto;

import lombok.Getter;

@Getter
public class LoginResponse {

    // Getters and Setters
    private String token;
    private String message;
    private Long userID;

    // Constructor
    public LoginResponse(String token, String message, Integer userID) {
        this.token = token;
        this.message = message;
        this.userID= Long.valueOf(userID);
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserID(Integer userID) {
        this.userID = Long.valueOf(userID);
    }
}