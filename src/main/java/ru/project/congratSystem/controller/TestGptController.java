package ru.project.congratSystem.controller;

import org.hibernate.validator.internal.util.privilegedactions.GetAnnotationAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.project.congratSystem.service.GptService;



@Controller
@RequestMapping("/simplePages")
public class TestGptController {

    private final GptService gptService;

    @Autowired
    public TestGptController(GptService gptService) {
        this.gptService = gptService;
    }


    @PostMapping("/gpt")
    public void SendQuestion(
            @RequestParam("question") String question
    ){
        System.out.println("работает");
        String answer = gptService.callPythonService(question);
        System.out.println(answer);

    }

    @GetMapping("/gpt")
    public String showGptPage(){
        return "simplePages/gpt";
    }
}
