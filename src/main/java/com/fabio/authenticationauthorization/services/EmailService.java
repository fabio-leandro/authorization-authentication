package com.fabio.authenticationauthorization.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService( final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String to,String title, String msg){
        var message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(title);
        message.setText(msg);
        javaMailSender.send(message);
    }
}
