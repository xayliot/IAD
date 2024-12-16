package ru.project.congratSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.project.congratSystem.model.Congratulation;
import ru.project.congratSystem.model.Friend;
import ru.project.congratSystem.model.User;
import ru.project.congratSystem.repository.UsersRepo;
import ru.project.congratSystem.service.CongratService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/congrats")
public class RestCongratsController {


    private final CongratService congratService;


    private final UsersRepo usersRepo;

    @Autowired
    public RestCongratsController(CongratService congratService, UsersRepo usersRepo) {
        this.congratService = congratService;
        this.usersRepo = usersRepo;
    }


    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> addCongrat(
            @RequestParam("title") String title,
            @RequestParam("text") String text,
            @RequestParam("friend_id") String friendId,
            @RequestParam("email") String email,
            @RequestParam("date") String date
    ){
        System.out.println(title);
        System.out.println(text);
        System.out.println(friendId);
        System.out.println(email);
        System.out.println(date);


        try {

            congratService.addCongrat(Long.parseLong(friendId), title, text, email);
            System.out.println("Плюс поздравление");
            Map<String, String> response = new HashMap<>();
            response.put("message", "Поздравление успешно добавлено");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Не удалось добавить поздравление.");
            System.out.println(e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        }
    }


    @GetMapping("/showCongrats")
    public ResponseEntity<List<Congratulation>> senListOfCongrats(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<User> userOptional = usersRepo.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOptional.get();
        List<Congratulation> congratulations = congratService.showAllCongrats(user);

        return new ResponseEntity<>(congratulations, HttpStatus.OK);
    }

}

