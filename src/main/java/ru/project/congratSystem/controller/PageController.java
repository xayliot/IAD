package ru.project.congratSystem.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.congratSystem.model.Friend;

@Controller
@RequestMapping("/simplePages")
public class PageController {

    @GetMapping("/main")
    public String showMain(){
        return "simplePages/main";
    }

    @GetMapping("/locked")
    public String showBlocked(){
        return "simplePages/locked";
    }



}
