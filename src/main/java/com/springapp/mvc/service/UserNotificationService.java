package com.springapp.mvc.service;

import com.springapp.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 * Created by VAHE on 20-Dec-16.
 */
@EnableAsync
@Service
public class UserNotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    public UserNotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendNotification(User user) throws MailException, InterruptedException {

        System.out.println("Sending email...");

        String subject = "Registration Confirmation";
        String confirmationUrl = "registrationConfirm?token=" + user.getSsoId();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setReplyTo(user.getEmail());
        mailMessage.setSubject(subject);
        mailMessage.setText("http://192.168.6.21:8080/" + confirmationUrl);
        javaMailSender.send(mailMessage);

        System.out.println("Email Sent!");
    }
}