package ru.project.congratSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;


@Service
public class EmailSendService {
    private final JavaMailSender mailSender;
//    private DynamicMailUsernameProvider dynamicMailUsernameProvider;

    @Autowired
    public EmailSendService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendMessage(String receiptAddress, String subject, String text) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);


        helper.setTo(receiptAddress);
        helper.setSubject(subject);
        helper.setText(text, true);

        mailSender.send(message);
    }



}
