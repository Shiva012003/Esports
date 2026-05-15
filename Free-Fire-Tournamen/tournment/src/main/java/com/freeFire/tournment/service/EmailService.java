package com.freeFire.tournment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationMail(
            String teamName,
            String tournament,
            String leader,
            String whatsapp
    ) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("kouluyshiva@gmail.com");

        message.setSubject("New Tournament Registration");

        message.setText(
                "Team Name: " + teamName +
                        "\nTournament: " + tournament +
                        "\nLeader: " + leader +
                        "\nWhatsApp: " + whatsapp
        );

        mailSender.send(message);
    }

    public void sendContactInquiry(String emailBody) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("kouluyshiva@gmail.com");

        message.setSubject("New Contact Inquiry - Free Fire Tournament");

        message.setText(emailBody);

        mailSender.send(message);
    }
}