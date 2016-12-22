package com.springapp.mvc.listener;

import com.springapp.mvc.event.UserRegistrationCompleteEvent;
import com.springapp.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by VAHE on 13-Dec-16.
 */
@Component
public class UserRegistrationListener implements ApplicationListener<UserRegistrationCompleteEvent> {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void onApplicationEvent(UserRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    @ResponseBody
    private void confirmRegistration(UserRegistrationCompleteEvent event) {
        User user = event.getUser();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + user.getSsoId();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("vahemikayelyan@gmail.com");
        mailMessage.setReplyTo("vahemikayelyan@gmail.com");
        mailMessage.setSubject(subject);
        mailMessage.setText("http://localhost:8080" + confirmationUrl);
        javaMailSender.send(mailMessage);
    }
}