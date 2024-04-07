package com.Project.springboot.Service;

import com.Project.springboot.BaseClasses.User;
import com.Project.springboot.Repository.UserRepository;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public User registerUser(String email, String rawPassword, String name) {
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);
        newUser.setName(name);
        // Set other necessary properties on newUser
        newUser.setRegistrationDate(new Date()); // Example of setting the registration date
        // Set other properties as needed

        return userRepository.save(newUser);
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<Integer> findUserIdByEmail(String email) {
        return userRepository.findByEmail(email).map(User::getUserID);
    }




    public User updateLastLogin(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setLastLogin(new Date()); // Set last login date to now
            return userRepository.save(user);
        }
        return null; // Handle this case according to your application's requirements
    }




    public ResponseEntity<User> deleteUser(Long id) {
        userRepository.deleteById(id);
        return null;
    }
}