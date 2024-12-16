package ru.project.congratSystem.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class ScheduledSendEmailService {
    private final JavaMailSender mailSender;


    @Autowired
    public ScheduledSendEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }



    public void sendScheduledMessage(String receiptEmailAddress, String titleOfMessage, String textOfMessage,
                                     Long date){

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               MimeMessage message = mailSender.createMimeMessage();
                               MimeMessageHelper helper = null;
                               try {
                                   helper = new MimeMessageHelper(message, true);
                                   helper.setTo(receiptEmailAddress);
                                   helper.setSubject(titleOfMessage);
                                   helper.setText(textOfMessage, true);
                                   System.out.println("раписание");
                               } catch (MessagingException e) {
                                   throw new RuntimeException(e);
                               }
                               mailSender.send(message);
                           }
                       }

        ,date);

    }

}
