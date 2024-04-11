package com.Project.springboot.Controller;

import com.Project.springboot.Dto.EmailDetailsDto; // Import the DTO class
import com.Project.springboot.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailDetailsDto emailDetails) {
        try {
            emailService.sendSimpleMessage(emailDetails.getTo(), emailDetails.getSubject(), emailDetails.getText());
            return "Email sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error in sending email: " + e.getMessage();
        }
    }
}
