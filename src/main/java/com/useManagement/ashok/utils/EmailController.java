

package com.useManagement.ashok.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {

    @Autowired
    public JavaMailSender emailSender;

    SimpleMailMessage message = new SimpleMailMessage();

    @ResponseBody
    public void newAccountConfirmation(String email, String firstName, String lastName, String password) {
        message.setTo(email);
        message.setSubject("Confirmation");
        message.setText("Dear " + firstName + " " + lastName + ", \n\n "
                + "Your account has been created successfully \n\n" +
                  "Your Temporary Password is:" + password + " \n\n"
                + "Thank you\n Ashok It\n ");
        // Send Message!
        this.emailSender.send(message);
    }

    public void sentTemporaryPassword(String emailId, String tempPassword) {

        message.setTo(emailId);
        message.setSubject("Confirmation");
        message.setText("Your Temporary Password is:" + tempPassword + " \n\n"
                + "Kindly Click on the link below to change your password\n Ashok It\n ");
        // Send Message!
        this.emailSender.send(message);
    }
}