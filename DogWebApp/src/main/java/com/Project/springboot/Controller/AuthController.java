package com.Project.springboot.Controller;

import com.Project.springboot.BaseClasses.User;
import com.Project.springboot.Dto.LoginRequest;
import com.Project.springboot.Dto.LoginResponse;
import com.Project.springboot.Security.AuthenticationService;
import com.Project.springboot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/auth") // r
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService; // Handles the actual authentication

    @Autowired
    public AuthController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            User user = authenticationService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
            userService.updateLastLogin(Long.valueOf(user.getUserID()));
            // Assuming you have some token generation logic for session management
            String token = authenticationService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(token, "Login successful", user.getUserID()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }
}