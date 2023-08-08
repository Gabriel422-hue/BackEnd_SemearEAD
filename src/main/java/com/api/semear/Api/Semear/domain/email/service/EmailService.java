package com.api.semear.Api.Semear.domain.email.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;


    public void enviarEmailConfirmacao(String addressee, String code) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(addressee);
        helper.setSubject("Confirmação de E-mail");
        helper.setText("Para confirmar seu e-mail, clique no link: " + "http://localhost:8080/confirmar-email/" + code);
        mailSender.send(message);
    }


}
