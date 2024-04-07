package com.Project.springboot.Security;
import com.Project.springboot.BaseClasses.User;
import com.Project.springboot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User authenticateUser(String email, String password) throws AuthenticationException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent() && passwordMatches(userOptional.get(), password)) {
            return userOptional.get();
        } else {
            throw new AuthenticationException("Invalid email or password.");
        }
    }

    private boolean passwordMatches(User user, String password) {
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    public String generateToken(User user) {
        // Token generation logic
        return "token"; // Placeholder for simplicity; you might use JWT or another method here
    }
}