package com.fabio.authenticationauthorization.services;

import com.fabio.authenticationauthorization.domain.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

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

    public void sendNewPasswordEmail(Customer customer, String newPass){
        SimpleMailMessage sm = prepareNewPasswordEmail(customer,newPass);
        send(customer.getEmail(), sm.getSubject(),sm.getText());
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Customer customer, String newPass){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(customer.getEmail());
        sm.setFrom(remetente);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: "+ newPass);
        return sm;
    }
}
