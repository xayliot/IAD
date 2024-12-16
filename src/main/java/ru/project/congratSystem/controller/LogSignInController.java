package ru.project.congratSystem.controller;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.congratSystem.model.User;
import ru.project.congratSystem.service.RegService;

@Controller
@RequestMapping("/authReg")
public class LogSignInController {
    private final RegService regService;

    public LogSignInController(RegService regService) {
        this.regService = regService;
    }

    @GetMapping("/auth")
    public String authPage(){

        return "authReg/auth";
    }

    @GetMapping("/reg")
    public String regPage(@ModelAttribute("user") User user){
        return "authReg/reg";
    }

    @PostMapping("/reg")
    public String createNewUser(@ModelAttribute("user") @Valid User user,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "authReg/reg";

        regService.signIn(user);
        return "redirect:simplePages/main";

    }
}
