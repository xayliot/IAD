package ru.project.congratSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.project.congratSystem.service.SmsService;

@Controller
@RequestMapping("/simplePages")
public class SmsController {

    private final SmsService smsService;

    @Autowired
    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @GetMapping("/sendSms")
    public String showSms(){
        return "simplePages/sendSms";
    }

    @PostMapping("/sendSms")
    public String sendForm(
            @RequestParam("toPhoneNumber") String toPhoneNumber,
            @RequestParam("message") String text
    ){
        if (!toPhoneNumber.startsWith("+")) {
            toPhoneNumber = "+" + toPhoneNumber;
        }
        System.out.println(toPhoneNumber + "   " + text);
        smsService.sendSms(toPhoneNumber, text);
        return "redirect:/simplePages/main";
    }
}
